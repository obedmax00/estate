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
        int expectedNumOfAgent = 4;
        for (Agent agent : agents) {
            System.out.println(agent);
        }
        Assert.assertEquals(expectedNumOfAgent, agents.size());
    }

    @Test
    public void setAgentTest(){
        System.out.println("setting agent");

        boolean i = agentDao.setAgent("mattcots","cots","matt","2345968585",
                "1234","119 fairfax va","mattcots@gmail.com");
        Assert.assertEquals(true,i);
    }

    @Test
    public void updateAgentTest(){
        System.out.println("updating agent");
        boolean i = agentDao.updateAgent("address","118 fairfax va","perterhan");
        Assert.assertEquals(true,i);
    }

    @Test
    public void deleteAgentTest(){
        System.out.println("deleting agent");

        boolean i = agentDao.deleteAgent("mattcots");
        Assert.assertEquals(true,i);
    }
}