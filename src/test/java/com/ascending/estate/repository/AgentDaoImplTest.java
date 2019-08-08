package com.ascending.estate.repository;

import com.ascending.estate.model.Agent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AgentDaoImplTest {
    private AgentDao agentDao;

    @Before
    public void init(){
        agentDao = new AgentDaoImpl();
    }
    @After
    public void cleanUp(){
        agentDao = null;
    }

    @Test
    public void saveAgent(){
        Agent agent = new Agent("scottlee","lee","scott","scottlee@gmail.com","3334442234",
                "1234","195 lee lynchburg va");
        agentDao.save(agent);

        Agent agent1 = agentDao.getAgentByName("scottlee");

        Assert.assertEquals(agent.getAddress(),agent1.getAddress());
    }
    @Test
    public void updateAgent(){
        Agent agent = new Agent("scottlee","lee","scott","scottlee@gmail.com","3334442234",
                "1234","1955 lee lynchburg va");
        agentDao.update(agent);
        Agent agent1 = agentDao.getAgentByName("scottlee");
        Assert.assertEquals(agent.getAddress(),agent1.getAddress());
    }
    @Test
    public void deleteAgent(){
        boolean flag = agentDao.delete("scottlee");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getAgents(){
        List<Agent> agents = agentDao.getAgents();
        Assert.assertEquals(4,agents.size());
    }

    @Test
    public void getAgentByName(){
        Agent agent = agentDao.getAgentByName("lukedj");
        Assert.assertEquals(1,agent.getId());
    }
}
