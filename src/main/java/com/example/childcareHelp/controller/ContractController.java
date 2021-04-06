package com.example.childcareHelp.controller;

import com.example.childcareHelp.DTO.ContractConditionDto;
import com.example.childcareHelp.DTO.LoginInfoDto;
import com.example.childcareHelp.DTO.UserInfoDto;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Contract;
import com.example.childcareHelp.entity.Family;
import com.example.childcareHelp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    @Autowired
    private FamilyService familyService;
    @Autowired
    private BabysitterService babysitterService;
    @Autowired
    private LoginService loginService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    /*
     * move to input the information of a contract
     */
    @GetMapping("/register/{snn}")
    public String inputContractInfo(@PathVariable Integer snn, @ModelAttribute("contract")  Contract contract, Model model) {
        System.out.println("[LOG]_ContractController_registerContract_Start");
        Optional<Babysitter> babysitter = babysitterService.getBabysitter(snn);
        babysitter.ifPresent(foundObject -> model.addAttribute("babysitter", foundObject));
        return "contract/contractRegister";
    }

    /*
     * register a contract between family and a babysitter
     */
    @PostMapping("/registerContract")
    public String registerContract(@ModelAttribute("contract")  Contract contract, Model model, HttpServletRequest req) {

        contract.setContractID(sequenceGeneratorService.generateSequence(Contract.SEQUENCE_NAME));
        UserInfoDto userInfoDto = (UserInfoDto)req.getSession().getAttribute("USER_INFO");
        contract.setFamilyID(userInfoDto.getId());
        contract.setStatus("REQUESTED");
        contractService.createContract(contract);
        return "redirect:contract/listOfRequestContracts";
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
    public String getListContractByCondition(@ModelAttribute("requestContractsCondition") ContractConditionDto contractConditionDto, Model model, HttpServletRequest req) {
        System.out.println("status : "+contractConditionDto.getStatus());
        System.out.println("month : "+contractConditionDto.getMonth());
        System.out.println("year : "+contractConditionDto.getYear());
        String tempDate = "";
        contractConditionDto.setStatus("REQUESTED");
        if (contractConditionDto.getYear() != null && !contractConditionDto.getYear().isEmpty()) {
            if (contractConditionDto.getMonth() != null && !contractConditionDto.getMonth().isEmpty()){
                tempDate = contractConditionDto.getYear() + "-" + contractConditionDto.getMonth();
            } else {
                tempDate = contractConditionDto.getYear();
            }
        }
        UserInfoDto userInfoDto = (UserInfoDto)req.getSession().getAttribute("USER_INFO");

        //return all contract list for current family to view.
        List<Contract> contractList = contractService.getAllContractsByCondition(userInfoDto.getId(),contractConditionDto.getStatus(),tempDate);
        model.addAttribute("contractList",contractList);
        return "contract/listOfRequestContracts";
    }

    /*
     * show the list of the accepted contracts by family (for babysitter)
     */
    @RequestMapping("/listOfAcceptContracts")
    public String getListContractByBabysitterId(@ModelAttribute("contract")  Contract contract, Model model) {
        model.addAttribute("contract", contractService.getAllContractsByBabysitterId(contract.getSnn()));
        return "contract/listOfAcceptContracts";
    }

}
