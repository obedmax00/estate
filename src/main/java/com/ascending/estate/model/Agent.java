package com.ascending.estate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "address")
    private String address;
//    @JsonIgnore

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private Set<Customer> customers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    public Agent(){}

    public Agent(String name,String lastName, String firstName, String email,
                 String phoneNumber, String password, String address){
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
    }
    public Agent(String name){
        this.name = name;
    }

    public String toString(){
        return "Id: " +getId()+ " Name: " +getName() + " LName: " + getLastName() + " FName: "
                + getFirstName() + " Address: " + getAddress()+ " Email: " + getEmail()
                + " PhoneNumber: " + getPhoneNumber() + " Password: " +getPassword();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public void setLastName(String last_name){
        this.lastName = last_name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setFirstName(String first_name){
        this.firstName = first_name;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPhoneNumber(String phone_number){
        this.phoneNumber = phone_number;
    }

    public String getPhoneNumber(){
        return phoneNumber;
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
        if(!name.equals(agent.name))
            return false;
        return true;
//        return id == agent.id &&
//                name.equals(agent.name) &&
//                phoneNumber.equals(agent.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Set<Customer> getCustomers() {
        try{
            int size = customers.size();
        }
        catch(Exception e){
            return null;
        }
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }


    public static void main(String[] args){
        Agent agent1 = new Agent("ryo");
        Agent agent2 = new Agent("ryo");
        Map<Agent,Integer> map = new HashMap<>();
        map.put(agent1,35);
        map.put(agent2,36);
        System.out.println(map.size());
    }
}
