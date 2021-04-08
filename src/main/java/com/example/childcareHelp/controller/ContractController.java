package com.example.childcareHelp.controller;

import com.example.childcareHelp.DTO.ContractConditionDto;
import com.example.childcareHelp.DTO.UserInfoDto;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Contract;
import com.example.childcareHelp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "redirect:/contract/listOfRequestContracts";
    }

    /*
     * show the detail information of a contract selected by babysitter
     */
    @RequestMapping("/detailOfAcceptContract/{contractId}")
    public String getAcceptContract(@PathVariable long contractId, Model model) {
        Contract contract = contractService.getContractsByContractId(contractId);
        model.addAttribute("contract",contract);
        return "contract/detailOfAcceptContract";
    }

    /*
     * show the detail information of a contract selected by family
     */
    @RequestMapping("/detailOfRequestContract/{contractId}")
    public String getRequestContract(@PathVariable long contractId, Model model) {
        Contract contract = contractService.getContractsByContractId(contractId);
        model.addAttribute("contract",contract);
        return "contract/detailOfRequestContract";
    }

    /*
     * cancel the status of a contract requested
     */
    @RequestMapping("/cancelContract/{contractId}")
    public String cancelContract(@PathVariable long contractId, Model model) {
        Contract contract = contractService.getContractsByContractId(contractId);
        contract.setStatus("CANCELED");
        contractService.updateContractStatus(contract);
        return "redirect:/contract/listOfRequestContracts";
    }

    /*
     * accept the status of a contract requested
     */
    @RequestMapping("/acceptContract/{contractId}")
    public String acceptContract(@PathVariable long contractId, Model model) {
        Contract contract = contractService.getContractsByContractId(contractId);
        contract.setStatus("ACCEPTED");
        contractService.updateContractStatus(contract);
        return "redirect:/contract/listOfAcceptContracts";
    }

    /*
     * deny the status of a contract requested
     */
    @RequestMapping("/denyContract/{contractId}")
    public String denyContract(@PathVariable long contractId, Model model) {
        Contract contract = contractService.getContractsByContractId(contractId);
        contract.setStatus("DENIED");
        contractService.updateContractStatus(contract);
        return "redirect:/contract/listOfAcceptContracts";
    }

    /*
     * show the list of the request contracts by oneself (for family)
     */
    @RequestMapping("/listOfRequestContracts")
    public String getListContractByConditionForFamily(@ModelAttribute("requestContractsCondition") ContractConditionDto contractConditionDto, Model model, HttpServletRequest req) {
        UserInfoDto userInfoDto = (UserInfoDto)req.getSession().getAttribute("USER_INFO");
        //return all contract list for current family to view.
        List<Contract> contractList = contractService.getAllContractsByConditionForFamily(userInfoDto.getId(),contractConditionDto);
        model.addAttribute("contractList",contractList);
        return "contract/listOfRequestContracts";
    }

    /*
     * show the list of the accepted contracts by family (for babysitter)
     */
    @RequestMapping("/listOfAcceptContracts")
    public String getListContractByConditionForBabysitter(@ModelAttribute("requestContractsCondition") ContractConditionDto contractConditionDto, Model model, HttpServletRequest req) {
        UserInfoDto userInfoDto = (UserInfoDto)req.getSession().getAttribute("USER_INFO");
        //return all contract list for current Babisitter to view.
        List<Contract> contractList = contractService.getAllContractsByConditionForBabysitter(userInfoDto.getId(), contractConditionDto);
        model.addAttribute("contractList",contractList);
        return "contract/listOfAcceptContracts";
    }

}
