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
    @Test
    public void setCustomerTest(){
        System.out.println("setting customer");

        boolean i = customerDao.setCustomer("patrickteng","teng","patrick","2948574839",
                95849,"854 fairfax va","patrickteng@gmail.com",2);
        Assert.assertEquals(true,i);
    }

    @Test
    public void updateCustomerTest(){
        System.out.println("updating customer");

        boolean i = customerDao.updateCustomer("salary","5832","amyjames");
        Assert.assertEquals(true,i);
    }
    @Test

    public void deleteCustomerTest(){
        System.out.println("deleting customer");

        boolean i = customerDao.deleteCustomer("patrickteng");
        Assert.assertEquals(true,i);
    }
}
