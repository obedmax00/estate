package com.ascending.estate.repository;

import com.ascending.estate.model.Customer;
import com.ascending.estate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        String hql = "FROM Customer";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Customer> query = session.createQuery(hql);
            return query.list();
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Customer getCustomerByName(String agentName) {
        if (agentName == null) return null;

        String hql = "FROM Customer as C left join fetch C.houses where lower(C.name) = :name";

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
}
