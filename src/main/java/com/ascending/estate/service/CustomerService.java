package com.ascending.estate.service;

import com.ascending.estate.model.Customer;
import com.ascending.estate.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public boolean save(Customer customer){return customerDao.save(customer);};
    public boolean update(Customer customer){return customerDao.update(customer);};
    public boolean delete(String customerName){return customerDao.delete(customerName);};
    public List<Customer> getCustomers(){return customerDao.getCustomers();};
    public Customer getCustomerByName(String agentName){return customerDao.getCustomerByName(agentName);};
    public boolean updateAgentRelation(String customerName, String agentName){
        return customerDao.updateAgentRelation(customerName,agentName);
    }
    public boolean updateHouseRelation(String customerName, String address){
        return customerDao.updateHouseRelation(customerName,address);
    }
}
