package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Contract;
import com.example.childcareHelp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private ChildService childService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private BabysitterService babysitterService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    /*
     * move to input the information of a contract
     */
    @GetMapping("/register")
    //@GetMapping("/registerContract")
    public String inputContractInfo(@ModelAttribute("contract")  Contract contract, Model model) {
        System.out.println("[LOG]_ContractController_registerContract_Start");
        return "contract/contractRegister";
    }

    /*
     * register a contract between family and a babysitter
     */
    @PostMapping("/registerContract")
    public String registerContract(@ModelAttribute("contract")  Contract contract, Model model) {

        contract.setContractID(sequenceGeneratorService.generateSequence(Contract.SEQUENCE_NAME));

        Optional<Babysitter> babysitter = babysitterService.getBabysitter(contract.getSnn());
        babysitter.ifPresent(foundObject->contract.setBabysitterName(foundObject.getName()));

        //add new contract to DB
        contractService.createContract(contract);

        //return all contract list for current family to view.
        model.addAttribute("allContracts",contractService.getAllContractsByFamilyId(contract.getFamilyID()));


        return "contract/listOfRequestContracts";
    }


    /*
     * show the detail information of a contract selected
     */
    @RequestMapping("/detailOfAcceptContract")
    public String getAcceptContract(@ModelAttribute("contract")  Contract contract, Model model) {
//        model.addAttribute(familyService.getFamily(contract.getFamilyID()));
//        model.addAttribute(childService.getAllChildByFamilyId(contract.getFamilyID()));
//        model.addAttribute(contractService.getContract(contract.getContractID()));
        return "contract/detailOfAcceptContract";
    }

    /*
     * show the detail information of a contract selected
     */
    @RequestMapping("/detailOfRequestContract")
    public String getRequestContract(@ModelAttribute("contract")  Contract contract, Model model) {
//        model.addAttribute(babysitterService.getBabysitter(contract.getSnn()));
//        model.addAttribute(contractService.getContract(contract.getContractID()));
        return "contract/detailOfRequestContract";
    }


    /*
     * update the status of a contract accepted
     */
    @RequestMapping("/updateContract")
    public String updateContract(@ModelAttribute("contract") Contract contract, Model model) {
//        model.addAttribute(contractService.updateContract(contract));
        return "contract/listOfAcceptContracts";
    }

    /*
     * cancel the status of a contract requested
     */
    @RequestMapping("/cancelContract")
    public String cancelContract(@ModelAttribute("contract")  Contract contract, Model model) {
//        model.addAttribute(contractService.updateContract(contract));
        return "contract/listOfRequestContracts";
    }

    /*
     * show the list of the request contracts by oneself (for family)
     */
    @RequestMapping("/listOfRequestContracts")
    public String getListContractByFamilyId(@ModelAttribute("contract")  Contract contract, Model model) {
        model.addAttribute("contract", contractService.getAllContractsByFamilyId(contract.getFamilyID()));
        return "contract/listOfRequestContracts";
    }

    /*
     * show the list of the accepted contracts by family (for babysitter)
     */
    @RequestMapping("/listOfAccecptContracts")
    public String getListContractByBabysitterId(@ModelAttribute("contract")  Contract contract, Model model) {
        model.addAttribute("contract", contractService.getAllContractsByBabysitterId(contract.getSnn()));
        return "contract/listOfAcceptContracts";
    }

}
