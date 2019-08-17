package com.ascending.estate.jdbc;


import com.ascending.estate.model.House;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class HouseDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/housing_db";
    static final String USER = "admin";
    static final String PASS = "1234!";

    public boolean updateHouse(String updateName, String value, String address) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "update house SET " + updateName + " = '" + value + "'" +
                    "where address = '" + address + "'";
            int i = stmt.executeUpdate(sql);
            if(i==1) {
                System.out.println("Updated a record.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteHouse(String address) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM house WHERE address = '" + address + "'";
            int i = stmt.executeUpdate(sql);
            if (i ==1) {
                System.out.println("Deleted a record.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }

    public boolean setHouse(String address, long price, int year,
                         String last_bought, String last_sold,
                         long tax, long customer_id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "insert into house (address,price,year," +
                    "last_bought,last_sold,tax,customer_id) values" +
                    "('" + address + "','" + price + "','"+year +
                    "','" + last_bought + "','" + last_sold +"','" +
                    tax + "','" + customer_id +"')";
            int i = stmt.executeUpdate(sql);
            if(i==1) {
                System.out.println("Created new record.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return false;
    }


    public List<House> getHouses(){
        List<House> houses = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "select * from house";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                long id = rs.getLong("id");
                String address = rs.getString("address");
                double price = rs.getDouble("price");
                int year = rs.getInt("year");
                Date last_bought = rs.getDate("last_bought");
                Date last_sold = rs.getDate("last_sold");
                double tax = rs.getDouble("tax");
//                long customer_id = rs.getLong("customer_id");


                House house = new House();
                house.setId(id);
                house.setAddress(address);
                house.setPrice(price);
                house.setYear(year);
                house.setLastBought(last_bought);
                house.setLastSold(last_sold);
                house.setTax(tax);
//                house.setCustomerId(customer_id);

                houses.add(house);

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return houses;
    }

//    public static void main(String[] args){
//        HouseDao houseDao = new HouseDao();
//        List<House> houses = houseDao.getHouses();
//
//        for (House house : houses){
//            System.out.println(house);
//        }
//
////        houseDao.deleteHouse("995 fairfax va");
////
////
////        houseDao.setHouse("133 fairfax va",12344,1995,"12/07/2008",
////                "12/23/1996",123,3);
////
////        houseDao.updateHouse("address", "995 fairfax va", "133 fairfax va");
//
//    }
}
