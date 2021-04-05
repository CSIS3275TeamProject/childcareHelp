package com.example.childcareHelp.service;

import com.example.childcareHelp.DAO.ContractDAO;
import com.example.childcareHelp.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractDAO contractDAO;

    public Collection<Contract> getAllContractsByFamilyId(Long familyId) {
        return contractDAO.getContractsByFamilyId(familyId);
    }
    public Collection<Contract> getAllContractsByBabysitterId(Integer snn) {
        return contractDAO.getContractsByBabysitterId(snn);
    }

    public Optional<Contract> getContract(Integer id) {
        return contractDAO.getContract(id);
    }

    public Contract createContract(Contract contract) {
        return contractDAO.createContract(contract);
    }

    public Contract updateContract(Contract contract) {
        return contractDAO.updateContract(contract);
    }

}
