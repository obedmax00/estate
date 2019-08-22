package com.ascending.estate.repository;
import com.ascending.estate.model.Agent;
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoImplTest {
    private CustomerDao customerDao;

    @Before
    public void init(){
        customerDao = new CustomerDaoImpl();
    }
    @After
    public void cleanUp(){
        customerDao = null;
    }

    @Test
    public void saveCustomer(){
        Customer customer = new Customer("aaronpaul","aaron","paul","aaronpaul@gmail.com","92 e street fairfax va",
                1234.12,"3468654432");
        customerDao.save(customer);

        Customer customer1 = customerDao.getCustomerByName("aaronpaul");

        Assert.assertEquals(customer.getAddress(),customer1.getAddress());
    }
    @Test
    public void updateCustomer(){
        Customer customer = customerDao.getCustomerByName("aaronpaul");
        customer.setAddress("925 e street fairfax va");
        customerDao.update(customer);
        Customer customer1 = customerDao.getCustomerByName("aaronpaul");
        Assert.assertEquals(customer.getAddress(),customer1.getAddress());
    }
    @Test
    public void deleteCustomer(){
        boolean flag = customerDao.delete("aaronpaul");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getCustomers(){
        List<Customer> customers = customerDao.getCustomers();
        customers.forEach(customer -> System.out.println(customer));

        Assert.assertEquals(3,customers.size());
    }

    @Test
    public void getCustomerByName(){
        Customer customer = customerDao.getCustomerByName("amyjames");
        customer.getHouses().forEach(house -> System.out.println(house));
        Assert.assertEquals(1,customer.getId());
    }

    @Test
    public void updateAgentRelation(){
        boolean isSuccess = customerDao.updateAgentRelation("aaronpaul","jamescook");
        Assert.assertEquals(true,isSuccess);
    }
    @Test
    public void updateHouseRelation(){
        boolean isSuccess = customerDao.updateHouseRelation("aaronpaul", "322 fairfax va");
        Assert.assertEquals(true,isSuccess);

    }
    @Test
    public void withdrawAgentRelation(){
        boolean isSuccess = customerDao.removeAgentRelation("aaronpaul");
        Assert.assertEquals(true,isSuccess);
    }
}
