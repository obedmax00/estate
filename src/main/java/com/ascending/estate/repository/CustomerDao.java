package com.ascending.estate.repository;

import com.ascending.estate.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean save(Customer customer);
    boolean update(Customer customer);
    boolean delete(String customerName);
    List<Customer> getCustomers();
    Customer getCustomerByName(String agentName);
    boolean updateAgentRelation(String customerName, String agentName);
    boolean updateHouseRelation(String customerName, String address);
}
