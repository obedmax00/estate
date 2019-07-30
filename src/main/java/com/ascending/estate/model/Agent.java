package com.ascending.estate.model;

import java.util.Objects;

public class Agent {
    private long id;
    private String name;
    private String last_name;
    private String first_name;
    private String email;
    private String phone_number;
    private String password;
    private String address;

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

    public void setLast_name(String last_name){
        this.last_name = last_name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public void setFirst_name(String first_name){
        this.first_name = first_name;
    }

    public String getFirst_name(){
        return this.first_name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }

    public String getPhone_number(){
        return phone_number;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return id == agent.id &&
                name.equals(agent.name) &&
                phone_number.equals(agent.phone_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone_number);
    }
}
