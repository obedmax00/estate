package com.ascending.estate.repository;

import com.ascending.estate.model.House;
import com.ascending.estate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HouseDaoImpl implements HouseDao{
    @Autowired
    private Logger logger;
    @Override
    public boolean save(House house){
        Transaction transaction = null;
        boolean isSuccess = true;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(house);
            transaction.commit();
        }catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The house %s was saved", house.toString()));
        return isSuccess;
    }
    @Override
    public boolean update(House house){
        Transaction transaction = null;
        boolean isSuccess = true;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(house);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        if (isSuccess) logger.debug(String.format("The house %s was updated",house.toString()));
        return isSuccess;
    }
    @Override
    public boolean delete(String houseAddress){
        String hql = "DELETE House where address = :houseName1";
        int deletedCount = 0;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<House> query = session.createQuery(hql);
            query.setParameter("houseName1", houseAddress);

            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }
        logger.debug(String.format("The house %s was deleted", houseAddress));
        return deletedCount >= 1 ? true : false;
    }

    @Override
    public List<House> getHouses() {
        String hql = "FROM House";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<House> query = session.createQuery(hql);
            return query.list();
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public House getHouseByName(String houseAddress) {
        if (houseAddress == null) return null;

        String hql = "FROM House H left join fetch H.customers where lower(H.address) = :name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<House> query = session.createQuery(hql);
            query.setParameter("name",houseAddress.toLowerCase());

            House house = query.uniqueResult();
            logger.debug(house.toString());
            return house;
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
