package com.ascending.estate.model;


import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address")
    private String address;
    @Column(name = "price")
    private long price;
    @Column(name = "year")
    private int year;
    @Column(name = "last_bought")
    private String last_bought;
    @Column(name = "last_sold")
    private String last_sold;
    @Column(name = "tax")
    private long tax;
    @Column(name = "customer_id")
    private long customer_id;

    public String toString(){
        return getAddress();
    }
    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setPrice(long price){
        this.price = price;
    }

    public long getPrice(){
    return this.price;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public void setLast_bought(String last_bought){
        this.last_bought = last_bought;
    }

    public String getLast_bought(){
        return this.last_bought;
    }

    public void setLast_sold(String last_sold){
        this.last_sold = last_sold;
    }

    public String getLast_sold(){
        return this.last_sold;
    }

    public void setTax(long tax){
        this.tax = tax;
    }

    public long getTax(){
        return this.tax;
    }

    public void setCustomer_id(long customer_id){
        this.customer_id = customer_id;
    }

    public long getCustomer_id(){
        return this.customer_id;
    }


}
