package com.ascending.estate.controller;

import com.ascending.estate.model.Customer;
import com.ascending.estate.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomerByName(@PathVariable String name){
        return customerService.getCustomerByName(name);
    }

    @RequestMapping(value="/{name}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteCustomer(@PathVariable String name){
        logger.debug("customer name: " + name);
        boolean isSuccess = customerService.delete(name);
        String msg = "the customer is deleted";
        if(isSuccess ==false){
            msg = "the customer is not deleted";
        }
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createCustomer(@RequestBody Customer customer){
        logger.debug("customer: " + customer.toString());
        String msg = "the customer is created.";
        boolean isSuccess = customerService.save(customer);
        if(isSuccess == false){
            msg = "the customer was not created";
        }
        return msg;
    }

    @RequestMapping(value="",method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateCustomer(@RequestBody Customer customer){
        logger.debug("customer:" + customer.toString());
        boolean isSuccess = customerService.update(customer);
        String msg = "the customer is updated";
        if(isSuccess ==false){
            msg = "the customer is not updated";
        }
        return msg;
    }


    @RequestMapping(value="/add-agent-relation/{customerName}/{agentName}",
            method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateCustomerAgentRelation(@PathVariable String customerName, @PathVariable String agentName){
        boolean isSuccess = customerService.updateAgentRelation(customerName,agentName);
        String msg = "the customer agent relation is updated";
        if(isSuccess ==false){
            msg = "the customer agent relation is not updated";
        }
        return msg;
    }

    @RequestMapping(value="/add-house-relation/{customerName}/{address}",
            method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateCustomerHouseRelation(@PathVariable String customerName, @PathVariable String address){
        boolean isSuccess = customerService.updateHouseRelation(customerName,address);
        String msg = "the customer house relation is updated";
        if(isSuccess ==false){
            msg = "the customer house relation is not updated";
        }
        return msg;
    }

    @RequestMapping(value="/remove-agent-relation/{customerName}",
            method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String removeAgentRelation(@PathVariable String customerName){
        boolean isSuccess = customerService.removeAgentRelation(customerName);
        String msg = "the customer house relation is removed";
        if(isSuccess ==false){
            msg = "the customer house relation is not removed";
        }
        return msg;
    }



}
