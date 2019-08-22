package com.ascending.estate.service;

import com.ascending.estate.model.Customer;
import com.ascending.estate.model.House;
import com.ascending.estate.repository.HouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class HouseService {
    @Autowired
    HouseDao houseDao;

    public boolean save(House house){return houseDao.save(house);};
    public boolean update(House house){return houseDao.update(house);};
    public boolean delete(String houseAddress){return houseDao.delete(houseAddress);};
    public List<House> getHouses(){return houseDao.getHouses();};
    public House getHouseByName(String houseAddress){return houseDao.getHouseByName(houseAddress);};

    public Set<Customer> viewCustomers(String address){
        return houseDao.getHouseByName(address).getCustomers();
    }
}
