package com.ascending.estate.repository;
import com.ascending.estate.model.Customer;
import org.junit.*;
import org.junit.runners.MethodSorters;
import java.util.List;
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
                1234.12,"3468654432",2);
        customerDao.save(customer);

        Customer customer1 = customerDao.getCustomerByName("aaronpaul");

        Assert.assertEquals(customer.getAddress(),customer1.getAddress());
    }
    @Test
    public void updateAgent(){
        Customer customer = customerDao.getCustomerByName("aaronpaul");
        customer.setAddress("925 e street fairfax va");
        customerDao.update(customer);
        Customer customer1 = customerDao.getCustomerByName("aaronpaul");
        Assert.assertEquals(customer.getAddress(),customer1.getAddress());
    }
    @Test
    public void deleteAgent(){
        boolean flag = customerDao.delete("aaronpaul");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getAgents(){
        List<Customer> customers = customerDao.getCustomers();
        customers.forEach(agent -> System.out.println(customers));

        Assert.assertEquals(3,customers.size());
    }

    @Test
    public void getAgentByName(){
        Customer customer = customerDao.getCustomerByName("amyjames");
        Assert.assertEquals(1,customer.getId());
    }
}
