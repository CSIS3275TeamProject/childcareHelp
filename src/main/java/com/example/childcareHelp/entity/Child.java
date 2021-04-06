package com.example.childcareHelp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Child")
public class Child {

//    @Transient
//    public static final String SEQUENCE_NAME = "child_sequence";

    @Id
    private long childID;
    private long familyID;
    private String name;
    private String gender;
    private String dateOfBirth;

    public long getChildID() {
        return childID;
    }

    public void setChildID(long childID) {
        this.childID = childID;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
