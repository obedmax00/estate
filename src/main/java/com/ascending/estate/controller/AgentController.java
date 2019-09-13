package com.ascending.estate.controller;

import com.ascending.estate.model.Agent;
import com.ascending.estate.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value={"/agents"})
public class AgentController {
    @Autowired
    private Logger logger;

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<Agent> getAgents(){return agentService.getAgents();}

    @RequestMapping(value="/{agentName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Agent getAgentByName(@PathVariable String agentName){
        return agentService.getAgentByName(agentName);
    }

    @RequestMapping(value="", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String creatAgent(@RequestBody Agent agent){
        logger.debug("agent: " + agent.toString());
        String msg = "the agent is created.";
        boolean isSuccess = agentService.save(agent);
        if(isSuccess == false){
            msg = "the department was not created";
        }
        return msg;
    }


    @RequestMapping(value="/{agentName}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteAgent(@PathVariable String agentName){
        logger.debug("agent name: " + agentName);
        boolean isSuccess = agentService.delete(agentName);
        String msg = "the agent is deleted";
        if(isSuccess ==false){
            msg = "the agent is not deleted";
        }
        return msg;
    }
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateAgent(@RequestBody Agent agent){
        logger.debug("agent:" + agent.toString());
        boolean isSuccess = agentService.update(agent);
        String msg = "the agent is updated";
        if(isSuccess ==false){
            msg = "the agent is not updated";
        }
        return msg;
    }
}
