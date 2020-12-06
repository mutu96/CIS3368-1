package com.finalproject.CIS3368.models;

import javax.persistence.*;

@Entity
@Table(name = "covid")
public class Corona {
    //calling covid table columns and storing in objects
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "Country")
    private String Country;
    @Column(name = "Tcase")
    private String Tcase;
    @Column(name = "Acase")
    private String Acase;
    @Column(name = "Rcase")
    private String Rcase;
    @Column(name = "Death")
    private String Death;
    @Column(name = "Date")
    private String Date;




    public Corona()
    {

    }
    public Corona(String Tcase, String id, String Acase, String Rcase, String Death, String Date, String Country) {

        this.id = id;
        this.Country = Country;
        this.Tcase = Tcase;
        this.Acase = Acase;
        this.Rcase = Rcase;
        this.Death = Death;
        this.Date = Date;


    }


    //using get and set function for column information
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getTcase() {
        return Tcase;
    }

    public void setTcase(String Tcase) {
        this.Tcase = Tcase;
    }

    public String getAcase() {
        return Acase;
    }

    public void setAcase(String Acase) {
        this.Acase = Acase;
    }

    public String getRcase() { return Rcase; }

    public void setRcase(String Rcase) {
        this.Rcase = Rcase;
    }

    public String getDeath() {
        return Death;
    }

    public void setDeath(String Death) {
        this.Death = Death;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }





}