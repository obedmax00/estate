package com.ascending.estate.controller;

import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import com.ascending.estate.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/houses")
public class HouseController {
    @Autowired
    HouseService houseService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value="", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<House> getHouses(){
        return houseService.getHouses();
    }

    @RequestMapping(value = "/{address}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public House getHouseByName(@PathVariable String address){
        return houseService.getHouseByName(address);
    }

    @RequestMapping(value="/{address}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteHouse(@PathVariable String address){
        logger.debug("house address: " + address);
        boolean isSuccess = houseService.delete(address);
        String msg = "the house is deleted";
        if(isSuccess ==false){
            msg = "the house is not deleted";
        }
        return msg;
    }

    @RequestMapping(value="", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createHouse(@RequestBody House house){
        logger.debug("house: " + house.toString());
        String msg = "the house is created.";
        boolean isSuccess = houseService.save(house);
        if(isSuccess == false){
            msg = "the house was not created";
        }
        return msg;
    }

    @RequestMapping(value="",method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateCustomer(@RequestBody House house){
        logger.debug("house:" + house.toString());
        boolean isSuccess = houseService.update(house);
        String msg = "the house is updated";
        if(isSuccess ==false){
            msg = "the house is not updated";
        }
        return msg;
    }

    @RequestMapping(value="/view-customers/{address}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Set<Customer> viewCustomers(@PathVariable String address){
        return houseService.viewCustomers(address);
    }


}
