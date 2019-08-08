package com.ascending.estate.repository;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AgentDaoImplTest.class,
        CustomerDaoImplTest.class,
        HouseDaoImplTest.class
})
public class TestHibernateAll {
}
