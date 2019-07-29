package com.ascending.estate.model;

public class Customer {
    private long id;
    private String name;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private long salary;
    private String phone_number;
    private long agent_id;

    public String toString(){
        return getName();
    }

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setFirst_name(String frist_name){
        this.first_name = first_name;
    }

    public String getFirst_name(){
        return this.first_name;
    }

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setSalary(long salary){
        this.salary = salary;
    }

    public long getSalary(){
        return this.salary;
    }

    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }

    public String getPhone_number(){
        return this.phone_number;
    }

    public void setAgent_id(long agent_id){
        this.agent_id = agent_id;
    }

    public long getAgent_id(){
        return agent_id;
    }
}
