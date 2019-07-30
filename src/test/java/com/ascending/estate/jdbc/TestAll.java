package com.ascending.estate.jdbc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AgentDaoTest.class,
        CustomerDaoTest.class,
        HouseDaoTest.class
})
public class TestAll {
}
