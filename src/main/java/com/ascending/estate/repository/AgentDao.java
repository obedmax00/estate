package com.ascending.estate.repository;

import com.ascending.estate.model.Agent;

import java.util.List;

public interface AgentDao {
    boolean save(Agent agent);
    boolean update(Agent agent);
    boolean delete(String agentName);
    List<Agent> getAgents();
    Agent getAgentByName(String agentName);
}
