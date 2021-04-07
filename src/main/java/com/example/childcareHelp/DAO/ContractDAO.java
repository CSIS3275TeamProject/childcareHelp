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


    public Contract getContractsByContractId(long contractId) {
        return contractRepository.findContractByContractID(contractId);
    }

    public List<Contract> getContractsByCondition(long familyId, String status, String yyyyMM) {
        return contractRepository.findAllByCondition(familyId, status, yyyyMM);
    }

    public Collection<Contract> getContractsByBabysitterId(long snn) {
        return contractRepository.findAllByBabysitterId(snn);
    }


    public Optional<Contract> getContract(Integer id) {
        return  contractRepository.findById(id);
    }

    public Contract createContract(Contract contract){
        return contractRepository.insert(contract);
    }


    public Contract updateContract(long contractId, String status) {
        return contractRepository.updateContract(contractId, status);
    }

}

