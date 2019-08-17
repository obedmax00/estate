package com.ascending.estate.repository;
import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class  HouseDaoImplTest {
    private HouseDao houseDao;

    @Before
    public void init(){
        houseDao = new HouseDaoImpl();
    }
    @After
    public void cleanUp(){
        houseDao = null;
    }

    @Test
    public void saveHouse(){
        Date lastBought = new Date(1995,2,10);
        Date lastSold = new Date(2008,2,20);
        House house = new House("1933 dj lynchburg va",199592,1966, lastBought,lastSold,
                1234.12);
        houseDao.save(house);

        House house1 = houseDao.getHouseByName("1933 dj lynchburg va");

        Assert.assertEquals(house.getLastBought(),house.getLastBought());
    }
    @Test
    public void updateHouse(){
        House house = houseDao.getHouseByName("1933 dj lynchburg va");
        house.setPrice(199500);
        houseDao.update(house);
        House house1 = houseDao.getHouseByName("1933 dj lynchburg va");
        Assert.assertEquals(house.getPrice(),house1.getPrice(),1);
    }
    @Test
    public void deleteHouse(){
        boolean flag = houseDao.delete("1933 dj lynchburg va");
        Assert.assertEquals(flag,true);
    }

    @Test
    public void getHouses(){
        List<House> houses = houseDao.getHouses();
        houses.forEach(house -> System.out.println(house));

        Assert.assertEquals(3,houses.size());
    }

    @Test
    public void getHouseByName(){
        House house = houseDao.getHouseByName("332 lynchburg va");
        house.getCustomers().forEach(customer -> System.out.println(customer));
        Assert.assertEquals(1,house.getId());
    }
}
