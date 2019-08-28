package com.ascending.estate.service;

import com.ascending.estate.init.AppInitializer;
import com.ascending.estate.model.Agent;
import com.ascending.estate.repository.AgentDao;
import com.ascending.estate.repository.AgentDaoImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class AgentServiceTest {
    @Autowired
    private AgentService agentService;

    @Before
    public void init(){
        //agentDao = new AgentDaoImpl();
    }
    @After
    public void cleanUp(){
        //agentDao = null;
    }

    @Test
    public void saveAgent(){
        Agent agent = new Agent("scottlee","lee","scott","scottlee@gmail.com","3334442234",
                "1234","195 lee lynchburg va");

        agentService.save(agent);

        Agent agent1 = agentService.getAgentByName("scottlee");

        Assert.assertEquals(agent.getAddress(),agent1.getAddress());
    }
    @Test
    public void updateAgent(){
        Agent agent = agentService.getAgentByName("scottlee");
        agent.setAddress("1955 lee lynchburg va");
        agentService.update(agent);
        Agent agent1 = agentService.getAgentByName("scottlee");
        Assert.assertEquals(agent1.getAddress(),agent.getAddress());
    }
    @Test
    public void deleteAgent(){
        boolean flag = agentService.delete("scottlee");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getAgents(){
        List<Agent> agents = agentService.getAgents();
        agents.forEach(agent -> System.out.println(agent));

        Assert.assertEquals(3,agents.size());
    }

    @Test
    public void getAgentByName(){
        Agent agent = agentService.getAgentByName("lukedj");
        System.out.println(agent.getCustomers());
        Assert.assertEquals(1,agent.getId());
    }

    @Test
    public void getAgentByCredentials(){
        Agent agent = agentService.getAgentByCredentials("lukedj@gmail.com","1234");
        Assert.assertEquals("lukedj",agent.getName());
    }
}
