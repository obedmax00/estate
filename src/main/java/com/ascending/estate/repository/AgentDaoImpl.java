package com.ascending.estate.repository;

import com.ascending.estate.model.Agent;
import com.ascending.estate.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AgentDaoImpl implements AgentDao {
    @Autowired
    private Logger logger;
    @Override
    public boolean save(Agent agent) {
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(agent);
            transaction.commit();
        } catch (Exception e) {
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The agent %s was saved", agent.toString()));
        return isSuccess;
    }
    @Override
    public boolean update(Agent agent){
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(agent);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The Agent %s was updated",agent.toString()));
        return isSuccess;
    }
    @Override
    public boolean delete(String agentName){
        String hql = "DELETE Agent where name = :agentName1";
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Agent> query = session.createQuery(hql);
            query.setParameter("agentName1", agentName);

            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The agent %s was deleted", agentName));
        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<Agent> getAgents() {
//        String hql = "FROM Agent as A left join fetch A.customers as c left join fetch c.houses";
        String hql = "FROM Agent";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Agent> query = session.createQuery(hql);
            return query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//            return query.list();
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Agent getAgentByName(String agentName) {
        if (agentName == null) return null;

        String hql = "FROM Agent as A left join fetch A.customers as C left join fetch C.houses where lower(A.name) = :name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Agent> query = session.createQuery(hql);
            query.setParameter("name",agentName.toLowerCase());

            Agent agent = query.uniqueResult();
            logger.debug(agent.toString());
            return agent;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
    //departments.forEach(dept -> System.out.println(dept));

    @Override
    public Agent getAgentByCredentials(String email, String password) {
        String hql = "FROM Agent as a where lower(a.email) = :email and a.password = :password";
        logger.debug(String.format("User email: %s, password: %s", email, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Agent> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        }
    }
}
