package com.ascending.estate.jdbc;

import com.ascending.estate.model.Agent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AgentDaoTest {
    private AgentDao agentDao;
    @Before
    public void init(){agentDao = new AgentDao();}

    @After
    public void cleanup(){
        agentDao = null;
    }

    @Test
    public void getAgentTest() {
        List<Agent> agents = agentDao.getAgents();
        int expectedNumOfAgent = 3;
        for (Agent agent : agents) {
            System.out.println(agent);
        }
        Assert.assertEquals(expectedNumOfAgent, agents.size());
    }
}