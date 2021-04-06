package com.example.childcareHelp.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.childcareHelp.DTO.LoginInfoDto;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.service.BabysitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/babysitter")
public class BabysitterController {

    @Autowired
    private BabysitterService babysitterService;

    @GetMapping("")
    @ResponseBody
    public Collection<Babysitter> getBabysitters() {
        return babysitterService.getAllBabysitters();
    }

    /* 
     * move to input the information of a babysitter
     */
    @RequestMapping("/register")
    public String inputBabysitterInfo(@ModelAttribute("babysitter")  Babysitter babysitter, Model model) {
        return "/babysitter/babysitterRegister";
    }

    /*
     * register the information of a babysitter
     */
    @PostMapping("/registerBabysitter")
    public String registerBabysitter(@ModelAttribute("babysitter")  Babysitter babysitter,Model model) {

        boolean result = babysitterService.checkExistedBabysitter(babysitter.getEmail());
        System.out.println("[LOG]_registerBabysitter_result="+result );
        if(!result) {
            model.addAttribute("babysitter", babysitterService.createBabysitter(babysitter));
        }
        else{
            model.addAttribute("ERROR_MESSAGE","The email you entered is already existed");
            return "/babysitter/babysitterRegister";
        }
        return "redirect:/login";
    }


    /*
     * show the list of babysitters
     */
    @RequestMapping("/listOfBabysitters")
    public String getListBabysitters(Model model) {
        List<Babysitter> babysitterList = babysitterService.getAllBabysitters();
        model.addAttribute("babysitters", babysitterList);
        return "babysitter/listOfBabysitters";
    }

    /*
     * show the detail information of a babysitter selected
     */
    @RequestMapping("/listOfBabysitters/{snn}")
    public String getBabysitter(@PathVariable Integer snn, Model model) {
        Optional<Babysitter> babysitter = babysitterService.getBabysitter(snn);
        babysitter.ifPresent(foundObject -> model.addAttribute("babysitter", foundObject));
        return "babysitter/detailOfBabysitter";
    }


    @RequestMapping("/listOfBabysitterss/{snn}")
    @ResponseBody
    public Optional<Babysitter> getBabysitter(@PathVariable Integer snn) {
         return babysitterService.getBabysitter(snn);
    }

    @RequestMapping("/listOfBabysitters/{input}/{gender}/{degree}/{age}")
    public String getBabysittersByCondition(@PathVariable("input") String input, @PathVariable("gender") String gender, @PathVariable("degree") String degree, @PathVariable("age") String age, Model model) {
        List<Babysitter> babysitters = babysitterService.getBabysittersByCondition(input, gender, degree, age);
        model.addAttribute("babysitters", babysitters);
        return "babysitter/listOfBabysitters";
    }


}
