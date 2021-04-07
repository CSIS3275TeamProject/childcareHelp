package com.example.childcareHelp.DTO;

import org.springframework.stereotype.Component;

@Component
public class ContractConditionDto {
    private String contractTitle;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }
}
