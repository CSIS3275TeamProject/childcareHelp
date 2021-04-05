package com.example.childcareHelp.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection ="Contract")
public class Contract {

    @Transient
    public static final String SEQUENCE_NAME = "contract_sequence";

    @Id
    private long contractID;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private String startDate;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private String endDate;
    @DateTimeFormat(pattern="HH:mm:ss")
    private String startTime;
    @DateTimeFormat(pattern="HH:mm:ss")
    private String endTime;
    @Value("${some.key:pending}")
    private String status;
    private Integer snn;
    private long familyID;
    private String babysitterName;


    public long getContractID() {
        return contractID;
    }

    public void setContractID(long contractID) {
        this.contractID = contractID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSnn() {
        return snn;
    }

    public void setSnn(Integer snn) {
        this.snn = snn;
    }

    public long getFamilyID() {
        return familyID;
    }

    public void setFamilyID(long familyID) {
        this.familyID = familyID;
    }

    public String getBabysitterName() {
        return babysitterName;
    }

    public void setBabysitterName(String babysitterName) {
        this.babysitterName = babysitterName;
    }
}
