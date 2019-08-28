package com.ascending.estate.controller;

import com.ascending.estate.model.Agent;
import com.ascending.estate.model.Customer;
import com.ascending.estate.service.AgentService;
import com.ascending.estate.service.CustomerService;
import com.ascending.estate.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {"/auth"})
public class Authentication {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AgentService agentService;
    @Autowired
    private CustomerService customerService;
    private String errorMsg = "The email or password is not correct";
    private String tokenKeyWord = "Authorization";
    private String tokenType = "Bearer";

    @RequestMapping(value = "/agent", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity authenticate(@RequestBody Agent agent) {
        String token = "";
        try {
            logger.debug(agent.toString());
            Agent u = agentService.getAgentByCredentials(agent.getEmail(), agent.getPassword());
            if (u == null)
                return ResponseEntity.status(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION).body(errorMsg);
            logger.debug(u.toString());
            token = JwtUtil.generateToken(u);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg == null) msg = "BAD REQUEST!";
            logger.error(msg);
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(msg);
        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(tokenKeyWord + ":" + tokenType + " " + token);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity authenticate(@RequestBody Customer customer) {
        String token = "";
        try {
            logger.debug(customer.toString());
            Customer u = customerService.getCustomerByCredentials(customer.getEmail(), customer.getPassword());
            if (u == null)
                return ResponseEntity.status(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION).body(errorMsg);
            logger.debug(u.toString());
            token = JwtUtil.generateToken(u);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg == null) msg = "BAD REQUEST!";
            logger.error(msg);
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(msg);
        }
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(tokenKeyWord + ":" + tokenType + " " + token);
    }
}
