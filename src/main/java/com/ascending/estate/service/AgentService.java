package com.ascending.estate.service;

import com.ascending.estate.model.Agent;
import com.ascending.estate.repository.AgentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AgentService {
    @Autowired
    private AgentDao agentDao;

    public boolean save(Agent agent){return agentDao.save(agent);};
    public boolean update(Agent agent){return agentDao.update(agent);};
    public boolean delete(String agentName){return agentDao.delete(agentName);};
    public List<Agent> getAgents(){return agentDao.getAgents();};
    public Agent getAgentByName(String agentName){return agentDao.getAgentByName(agentName);};
}
