package com.example.childcareHelp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection ="Family")
public class Family {

    @Transient
    public static final String SEQUENCE_NAME = "family_sequence";

    @Id
    private long familyID;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String maritalStatus;

    private List<Child> children = new ArrayList<>();

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> child) {
        this.children = child;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public long getFamilyID() {
        return familyID;
    }

    public void setFamilyID(long familyID) {
        this.familyID = familyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
