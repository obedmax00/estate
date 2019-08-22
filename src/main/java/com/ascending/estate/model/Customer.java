package com.ascending.estate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "salary")
    private double salary;
    @Column(name = "phone_number")
    private String phoneNumber;

//    delete this property
//    @Column(name = "agent_id")
//    private long agentId;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

//    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customers_houses",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "house_id") }
    )
    private Set<House> houses;





    public Customer(){}

    public Customer(String name, String firstName, String lastName, String email,
                    String address, double salary, String phoneNumber){
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
//        this.agentId = agentId;
    }

    public String toString(){
        return getName();
    }

    public void setId(long id){
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Set<House> getHouses() {
        try{
            int size = houses.size();
        }
        catch(Exception e){
            return null;
        }
        return houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
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

    public void setFirstName(String first_name){
        this.firstName = first_name;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String last_name){
        this.lastName = last_name;
    }

    public String getLastName(){
        return this.lastName;
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

    public void setSalary(double salary){
        this.salary = salary;
    }

    public double getSalary(){
        return this.salary;
    }

    public void setPhoneNumber(String phone_number){
        this.phoneNumber = phone_number;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

//    public void setAgentId(long agent_id){
//        this.agentId = agent_id;
//    }
//
//    public long getAgentId(){
//        return agentId;
//    }
}
