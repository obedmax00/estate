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
        int expectedNumOfHouse = 4;
        for (House house : houses) {
            System.out.println(house);
        }
        Assert.assertEquals(expectedNumOfHouse, houses.size());

    }
}
