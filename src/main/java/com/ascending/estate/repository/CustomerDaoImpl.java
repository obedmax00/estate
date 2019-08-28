package com.ascending.estate.repository;

import com.ascending.estate.model.Agent;
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import com.ascending.estate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CustomerDaoImpl implements CustomerDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean save(Customer customer) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The customer %s was saved", customer.toString()));
        return isSuccess;
    }
    @Override
    public boolean update(Customer customer){
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The customer %s was updated",customer.toString()));
        return isSuccess;
    }
    @Override
    public boolean delete(String customerName){
        String hql = "DELETE Customer where name = :customerName1";
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("customerName1", customerName);

            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The customer %s was deleted", customerName));
        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Customer> getCustomers() {
//        String hql = "FROM Customer C left join fetch C.houses";
        String hql = "FROM Customer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
//            return query.list();
            return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer getCustomerByName(String agentName) {
        if (agentName == null) return null;

        String hql = "FROM Customer as C left join fetch C.houses left join fetch C.agent where lower(C.name) = :name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("name",agentName.toLowerCase());

            Customer customer = query.uniqueResult();
            logger.debug(customer.toString());
            return customer;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateAgentRelation(String customerName, String agentName){
        Transaction transaction = null;
        boolean isSuccess = true;
        String hqlCustomer = "FROM Customer C where lower(C.name) = :customerName";
        String hqlAgent = "FROM Agent A where lower(A.name) = :agentName";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Agent> queryA = session.createQuery(hqlAgent);
            queryA.setParameter("agentName",agentName.toLowerCase());
            Query<Customer> queryC = session.createQuery(hqlCustomer);
            queryC.setParameter("customerName",customerName.toLowerCase());

            Customer customer = queryC.uniqueResult();
            Agent agent = queryA.uniqueResult();

            customer.setAgent(agent);

            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug("The customer and agent relation was updated");
        return isSuccess;
    }

    @Override
    public boolean updateHouseRelation(String customerName, String address){
        Transaction transaction = null;
        boolean isSuccess = true;
        String hqlCustomer = "FROM Customer C left join fetch C.houses where lower(C.name) = :CustomerName";
        String hqlHouse = "FROM House H where lower(H.address) = :HouseName";
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<House> queryH = session.createQuery(hqlHouse);
            queryH.setParameter("HouseName",address.toLowerCase());
            Query<Customer> queryC = session.createQuery(hqlCustomer);
            queryC.setParameter("CustomerName",customerName.toLowerCase());

            Customer customer = queryC.uniqueResult();
            House house = queryH.uniqueResult();
            Set<House> houses = new HashSet<>();
            houses.add(house);
            houses.addAll(customer.getHouses());
            customer.setHouses(houses);

            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug("The customer and agent relation was updated");
        return isSuccess;
    }

    @Override
    public boolean removeAgentRelation(String customerName){
        Transaction transaction = null;
        String hql = "FROM Customer C where lower(C.name) = :CustomerName";
        boolean isSuccess = true;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("CustomerName",customerName.toLowerCase());

            Customer customer = query.uniqueResult();
            customer.setAgent(null);

            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if(getCustomerByName(customerName).getAgent() != null) {
            isSuccess =false;
        }
        if (isSuccess){
            logger.debug("The customer and agent relation was removed");
        }
        return isSuccess;
    }

    @Override
    public Customer getCustomerByCredentials(String email, String password) {
        String hql = "FROM Customer as C where lower(C.email) = :email and C.password = :password";
        logger.debug(String.format("User email: %s, password: %s", email, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        }
    }

}
