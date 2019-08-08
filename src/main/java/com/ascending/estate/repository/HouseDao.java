package com.ascending.estate.repository;

import com.ascending.estate.model.House;

import java.util.List;

public interface HouseDao {
    boolean save(House house);
    boolean update(House house);
    boolean delete(String houseAddress);
    List<House> getHouses();
    House getHouseByName(String houseAddress);
}
