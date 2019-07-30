package com.ascending.estate.jdbc;

import com.ascending.estate.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/housing_db";
    static final String USER = "admin";
    static final String PASS = "1234!";

    public boolean updateCustomer(String updateName, String value, String record) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "update customer SET " + updateName + " = '" + value + "'" +
                    "where name = '" + record + "'";
            int i = stmt.executeUpdate(sql);
            if(i ==1) {
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

    public boolean deleteCustomer(String name) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM customer WHERE name = '" + name + "'";
            int i = stmt.executeUpdate(sql);
            if(i==1) {
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

    public boolean setCustomer(String name, String last_name, String first_name, String phone_number,
                          int salary, String address, String email, long agent_id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "insert into customer (name, last_name,first_name," +
                    "email,phone_number,salary,address,agent_id) values" +
                    "('" + name + "','" + last_name + "','"+first_name +
                    "','" + email + "','" + phone_number +"','" +
                    salary + "','" + address + "','" + agent_id +"')";
            int i = stmt.executeUpdate(sql);
            if (i==1) {
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

    public List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "select * from customer";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String last_name = rs.getString("last_name");
                String first_name = rs.getString("first_name");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                long salary = rs.getLong("salary");
                long agent_id = rs.getLong("agent_id");


                Customer customer = new Customer();
                customer.setId(id);
                customer.setLast_name(last_name);
                customer.setFirst_name(first_name);
                customer.setEmail(email);
                customer.setPhone_number(phone_number);
                customer.setAddress(address);
                customer.setName(name);
                customer.setSalary(salary);
                customer.setAgent_id(agent_id);


                customers.add(customer);

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
        return customers;
    }


}
