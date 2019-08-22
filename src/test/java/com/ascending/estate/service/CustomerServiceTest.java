package com.ascending.estate.service;

import com.ascending.estate.init.AppInitializer;
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import com.ascending.estate.repository.AgentDao;
import com.ascending.estate.repository.AgentDaoImpl;
import com.ascending.estate.repository.HouseDao;
import com.ascending.estate.repository.HouseDaoImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private HouseService houseService;
    @Test
    public void saveCustomer(){
        Customer customer = new Customer("aaronpaul","aaron","paul","aaronpaul@gmail.com","92 e street fairfax va",
                1234.12,"3468654432");

        customerService.save(customer);

        Customer customer1 = customerService.getCustomerByName("aaronpaul");

        Assert.assertEquals(customer.getAddress(),customer1.getAddress());
    }
    @Test
    public void updateCustomer(){
        Customer customer = customerService.getCustomerByName("aaronpaul");
        customer.setAddress("925 e street fairfax va");
        customer.setAgent(null);
        customerService.update(customer);
        Customer customer1 = customerService.getCustomerByName("aaronpaul");
        Assert.assertEquals(customer.getAddress(),customer1.getAddress());
    }
    @Test
    public void deleteCustomer(){
        boolean flag = customerService.delete("aaronpaul");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        customers.forEach(customer -> System.out.println(customer));

        Assert.assertEquals(3,customers.size());
    }

    @Test
    public void getCustomerByName(){
        Customer customer = customerService.getCustomerByName("amyjames");
        customer.getHouses().forEach(house -> System.out.println(house));
        Assert.assertEquals(1,customer.getId());
    }

    @Test
    public void updateAgentRelation(){
        boolean isSuccess = customerService.updateAgentRelation("aaronpaul","jamescook");
        Assert.assertEquals(true,isSuccess);
    }
    @Test
    public void updateHouseRelation(){
        boolean isSuccess = customerService.updateHouseRelation("aaronpaul", "322 fairfax va");
        Assert.assertEquals(true,isSuccess);

    }

    @Test
    public void withdrawAgentRelation(){
        boolean isSuccess = customerService.removeAgentRelation("aaronpaul");
        Assert.assertEquals(true,isSuccess);
    }
}
