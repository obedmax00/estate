package com.ascending.estate.jdbc;
import com.ascending.estate.model.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AgentDao {
    //STEP 1: Database information
    static final String DB_URL = "jdbc:postgresql://localhost:5432/housing_db";
    static final String USER = "admin";
    static final String PASS = "1234!";

//    private Logger logger = LoggerFactory.getLogger((this.getClass()));

    public boolean updateAgent(String updateName, String value, String record) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "update agent SET " + updateName + " = '" + value + "'" +
                    "where name = '" + record + "'";
            int i = stmt.executeUpdate(sql);

            if(i == 1){
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

    public boolean deleteAgent(String name) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM agent WHERE name = '" + name + "'";
            int i = stmt.executeUpdate(sql);
            if (i == 1){
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

    public boolean setAgent(String name, String last_name, String first_name, String phone_number,
                          String password, String address, String email) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "insert into agent (name, last_name,first_name," +
                    "email,phone_number,password,address) values" +
                    "('" + name + "','" + last_name + "','"+first_name +
                    "','" + email + "','" + phone_number +"','" +
                    password + "','" + address + "')";
            int i = stmt.executeUpdate(sql);
            if(i==1){
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

    public List<Agent> getAgents(){
        List<Agent> agents = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs =null;
        try{
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER, PASS);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "select * from agent";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                long id = rs.getLong("id");
                String last_name = rs.getString("last_name");
                String first_name = rs.getString("first_name");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                String password = rs.getString("password");
                String name = rs.getString("name");

                Agent agent = new Agent();
                agent.setId(id);
                agent.setLast_name(last_name);
                agent.setFirst_name(first_name);
                agent.setEmail(email);
                agent.setPhone_number(phone_number);
                agent.setAddress(address);
                agent.setPassword(password);
                agent.setName(name);

                agents.add(agent);

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
//        logger.debug(String.format("printing agent object size %d"),agents.size());

        return agents;
    }


}
