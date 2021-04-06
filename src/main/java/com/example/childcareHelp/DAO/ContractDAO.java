package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ContractDAO {

    @Autowired
    private ContractRepository contractRepository;

    public Collection<Contract> getContractsByFamilyId(long familyId) {
        return contractRepository.findAllByFamilyId(familyId);
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

    public Contract updateContract(Contract contract){
        return contractRepository.save(contract);
    }

}

