package com.example.childcareHelp.DTO;

import org.springframework.stereotype.Component;

@Component
public class ContractConditionDto {
    private String year;
    private String month;
    private String status;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
