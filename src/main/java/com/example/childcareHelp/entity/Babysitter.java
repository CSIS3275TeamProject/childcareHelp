package com.example.childcareHelp.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


@Document(collection ="Babysitter")
public class Babysitter {

    @Id
    private long snn;
    private String name;
    private String password;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private String dateOfBirth;
    private String gender;
    private String email;
    private String highestEducation;
    private String phoneNumber;

    public long getSnn() {
        return snn;
    }

    public void setSnn(long snn) {
        this.snn = snn;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String heighestEducation) {
        this.highestEducation = heighestEducation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
