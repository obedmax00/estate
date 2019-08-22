package com.ascending.estate.service;


import com.ascending.estate.init.AppInitializer;
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class HouseServiceTest{
    @Autowired
    private HouseService houseService;

    @Test
    public void saveHouse(){
        Date lastBought = new Date(1995,2,10);
        Date lastSold = new Date(2008,2,20);
        House house = new House("1933 dj lynchburg va",199592,1966, lastBought,lastSold,
                1234.12);
        houseService.save(house);

        House house1 = houseService.getHouseByName("1933 dj lynchburg va");

        Assert.assertEquals(house.getLastBought(),house.getLastBought());
    }
    @Test
    public void updateHouse(){
        House house = houseService.getHouseByName("1933 dj lynchburg va");
        house.setPrice(199500);
        houseService.update(house);
        House house1 = houseService.getHouseByName("1933 dj lynchburg va");
        Assert.assertEquals(house.getPrice(),house1.getPrice(),1);
    }
    @Test
    public void deleteHouse(){
        boolean flag = houseService.delete("1933 dj lynchburg va");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getHouses(){
        List<House> houses = houseService.getHouses();
        houses.forEach(house -> System.out.println(house));

        Assert.assertEquals(3,houses.size());
    }

    @Test
    public void getHouseByName(){
        House house = houseService.getHouseByName("332 lynchburg va");
        house.getCustomers().forEach(customer -> System.out.println(customer));
        Assert.assertEquals(1,house.getId());
    }
    @Test
    public void viewCustomers(){
        Set<Customer> customers = houseService.viewCustomers("332 lynchburg va");
        Assert.assertEquals("amyjames",customers.stream().findFirst().get().getName());
    }
}


