package com.ascending.estate.jdbc;

import com.ascending.estate.model.House;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HouseDaoTest {
    private HouseDao houseDao;
    @Before
    public void init(){houseDao = new HouseDao();}

    @After
    public void cleanup(){
        houseDao = null;
    }

    @Test
    public void getHouseTest() {
        HouseDao houseDao = new HouseDao();
        List<House> houses = houseDao.getHouses();
        int expectedNumOfHouse = 5;
        for (House house : houses) {
            System.out.println(house);
        }
        Assert.assertEquals(expectedNumOfHouse, houses.size());

    }
    @Test
    public void setHouseTest(){
        System.out.println("setting house");
        boolean i = houseDao.setHouse("789 fairfax va",12399,1995,"03/04/1995",
                "01/04/2014",124,2);
        Assert.assertEquals(true,i);
    }
    @Test
    public void updateHouseTest(){
        System.out.println("updating house");

        boolean i = houseDao.updateHouse("price","12933","1995 falls church va");
        Assert.assertEquals(true,i);
    }

    @Test
    public void deleteHouseTest(){
        System.out.println("deleting house");

        boolean i = houseDao.deleteHouse("789 fairfax va");
        Assert.assertEquals(true,i);
    }
}
