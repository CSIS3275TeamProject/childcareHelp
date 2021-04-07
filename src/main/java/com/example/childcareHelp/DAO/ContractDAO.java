package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class ContractDAO {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getContractsByFamilyId(long familyId) {
        return contractRepository.findAllByFamilyId(familyId);
    }

    public List<Contract> getContractsByBabysitterId(long snn) {
        return contractRepository.findAllByBabysitterId(snn);
    }

    public Contract getContractsByContractId(long contractId) {
        return contractRepository.findContractByContractID(contractId);
    }

    public List<Contract> getContractsByConditionForFamily(long familyId, String contractTitle, String status) {
        return contractRepository.findAllByConditionForFamily(familyId, contractTitle, status);
    }

    public List<Contract> getContractsByStatusForFamily(long familyId, String status) {
        return contractRepository.findAllByStatusForFamily(familyId, status);
    }
    public List<Contract> getContractsByTitleForFamily(long familyId, String contractTitle) {
        return contractRepository.findAllByTitleForFamily(familyId, contractTitle);
    }

    public List<Contract> getContractsByConditionForBabysitter(long snn, String contractTitle, String status) {
        return contractRepository.findAllByConditionForBabysitter(snn, contractTitle, status);
    }

    public List<Contract> getContractsByStatusForBabysitter(long snn, String status) {
        return contractRepository.findAllByStatusForBabysitter(snn, status);
    }

    public List<Contract> getContractsByTitleForBabysitter(long snn, String contractTitle) {
        return contractRepository.findAllByTitleForBabysitter(snn, contractTitle);
    }


    public Contract createContract(Contract contract){
        return contractRepository.insert(contract);
    }

    public Contract updateContractStatus(Contract contract) {
        return contractRepository.save(contract);
    }
}

