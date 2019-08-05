package com.ascending.estate.repository;

import com.ascending.estate.model.Agent;
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import com.ascending.estate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
public class HibernateMappingTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void mappingAgentTest() {
        String hql = "FROM Agent";
        List<Agent> agents = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Agent> query = session.createQuery(hql);
            agents = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(agents);
    }
    @Test
    public void mappingHouseTest() {
        String hql = "FROM House";
        List<House> houses = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<House> query = session.createQuery(hql);
            houses = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(houses);
    }
    @Test
    public void mappingCustomerTest() {
        String hql = "FROM Customer";
        List<Customer> customers = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            customers = query.list();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
        Assert.assertNotNull(customers);
    }
}