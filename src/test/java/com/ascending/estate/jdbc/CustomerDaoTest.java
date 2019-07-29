package com.ascending.estate.jdbc;


import com.ascending.estate.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CustomerDaoTest {
    private CustomerDao customerDao;
    @Before
    public void init(){customerDao = new CustomerDao();}

    @After
    public void cleanup(){
        customerDao = null;
    }

    @Test
    public void getCustomerTest() {
        CustomerDao customerDao = new CustomerDao();
        List<Customer> customers = customerDao.getCustomers();
        int expectedNumOfCustomer = 3;
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        Assert.assertEquals(expectedNumOfCustomer, customers.size());

    }
}
