package com.example.childcareHelp.service;

import com.example.childcareHelp.DAO.BabysitterDAO;
import com.example.childcareHelp.DAO.ContractDAO;
import com.example.childcareHelp.DAO.FamilyDAO;
import com.example.childcareHelp.DTO.ContractConditionDto;
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

    public List<Contract> getAllContractsByConditionForFamily(long familyId, ContractConditionDto condition) {
        List<Contract> contractList = null;
        String contractTitle = condition.getContractTitle();
        String status = condition.getStatus();
        if (status != null && !status.isEmpty() && contractTitle != null && !contractTitle.isEmpty()) {
            contractList = contractDAO.getContractsByConditionForFamily(familyId, contractTitle, status);
        } else if (status != null && !status.isEmpty() && (contractTitle == null || contractTitle.isEmpty())) {
            contractList = contractDAO.getContractsByStatusForFamily(familyId, status);
        } else if ((status == null || status.isEmpty()) && contractTitle != null && !contractTitle.isEmpty()) {
            contractList = contractDAO.getContractsByTitleForFamily(familyId, contractTitle);
        } else {
            contractList = contractDAO.getContractsByFamilyId(familyId);
        }
        for(int i=0; i < contractList.size(); i++) {
            Contract contract = contractList.get(i);
            contractList.get(i).setFamily(familyDAO.getFamilyByFamilyId(contractList.get(i).getFamilyID()));
            Optional<Babysitter> babysitter = babysitterDAO.getBabysitterBySnn(contractList.get(i).getSnn());
            babysitter.ifPresent(foundObject -> contract.setBabysitter(foundObject));
        }
        return contractList;
    }

    public List<Contract> getAllContractsByConditionForBabysitter(long snn, ContractConditionDto condition) {
        List<Contract> contractList = null;

        String contractTitle = condition.getContractTitle();
        String status = condition.getStatus();
        if (status != null && !status.isEmpty() && contractTitle != null && !contractTitle.isEmpty()) {
            contractList = contractDAO.getContractsByConditionForBabysitter(snn, contractTitle, status);
        } else if (status != null && !status.isEmpty() && (contractTitle == null || contractTitle.isEmpty())) {
            contractList = contractDAO.getContractsByStatusForBabysitter(snn, status);
        } else if ((status == null || status.isEmpty()) && contractTitle != null && !contractTitle.isEmpty()) {
            contractList = contractDAO.getContractsByTitleForBabysitter(snn, contractTitle);
        } else {
            contractList = contractDAO.getContractsByBabysitterId(snn);
        }


        for(int i=0; i < contractList.size(); i++) {
            Contract contract = contractList.get(i);
            contractList.get(i).setFamily(familyDAO.getFamilyByFamilyId(contractList.get(i).getFamilyID()));
            Optional<Babysitter> babysitter = babysitterDAO.getBabysitterBySnn(contractList.get(i).getSnn());
            babysitter.ifPresent(foundObject -> contract.setBabysitter(foundObject));
        }
        return contractList;
    }

    public Contract createContract(Contract contract) {
        return contractDAO.createContract(contract);
    }

    public Contract updateContractStatus(Contract contract) {
        return contractDAO.updateContractStatus(contract);
    }
}
