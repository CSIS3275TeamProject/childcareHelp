package com.example.childcareHelp.service;

import com.example.childcareHelp.DAO.BabysitterDAO;
import com.example.childcareHelp.DAO.ContractDAO;
import com.example.childcareHelp.DAO.FamilyDAO;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Contract;
import com.example.childcareHelp.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private FamilyDAO familyDAO;
    @Autowired
    private BabysitterDAO babysitterDAO;

    public Contract getContractsByContractId(long contrcatId) {
        Contract contract = contractDAO.getContractsByContractId(contrcatId);
        contract.setFamily(familyDAO.getFamilyByFamilyId(contract.getFamilyID()));
        Optional<Babysitter> babysitter = babysitterDAO.getBabysitterBySnn(contract.getSnn());
        babysitter.ifPresent(foundObject -> contract.setBabysitter(foundObject));
        return contract;
    }

    public List<Contract> getAllContractsByCondition(long familyId, String status, String yyyyMM) {
        List<Contract> contractList = contractDAO.getContractsByCondition(familyId, status, yyyyMM);
        for(int i=0; i < contractList.size(); i++) {
            Contract contract = contractList.get(i);
            contractList.get(i).setFamily(familyDAO.getFamilyByFamilyId(contractList.get(i).getFamilyID()));
            Optional<Babysitter> babysitter = babysitterDAO.getBabysitterBySnn(contractList.get(i).getSnn());
            babysitter.ifPresent(foundObject -> contract.setBabysitter(foundObject));
        }
        return contractList;
    }

    public Collection<Contract> getAllContractsByBabysitterId(long snn) {
        return contractDAO.getContractsByBabysitterId(snn);
    }

    public Optional<Contract> getContract(Integer id) {
        return contractDAO.getContract(id);
    }

    public Contract createContract(Contract contract) {
        return contractDAO.createContract(contract);
    }

    public Contract updateContract(long contractId, String status) {
        return contractDAO.updateContract(contractId, status);
    }
}
