package com.ascending.estate.model;


import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address")
    private String address;
    @Column(name = "price")
    private double price;
    @Column(name = "year")
    private int year;
    @Column(name = "last_bought")
    private Date lastBought;
    @Column(name = "last_sold")
    private Date lastSold;
    @Column(name = "tax")
    private double tax;
    @Column(name = "customer_id")
    private long customerId;


    public House(){}

    
    public House(String address, double price, int year, Date lastBought,
                 Date lastSold, double tax, long customerId){
        this.address = address;
        this.price = price;
        this.year = year;
        this.lastBought = lastBought;
        this.lastSold = lastSold;
        this.tax = tax;
        this.customerId = customerId;
    }

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

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
    return this.price;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public void setLastBought(Date last_bought){
        this.lastBought = last_bought;
    }

    public Date getLastBought(){
        return this.lastBought;
    }

    public void setLastSold(Date last_sold){
        this.lastSold = last_sold;
    }

    public Date getLastSold(){
        return this.lastSold;
    }

    public void setTax(double tax){
        this.tax = tax;
    }

    public double getTax(){
        return this.tax;
    }

    public void setCustomerId(long customer_id){
        this.customerId = customer_id;
    }

    public long getCustomerId(){
        return this.customerId;
    }


}
