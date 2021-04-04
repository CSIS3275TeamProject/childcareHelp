package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.service.BabysitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/babysitter")
public class BabysitterController {

    @Autowired
    private BabysitterService babysitterService;

    /*
     * move to input the information of a babysitter
     */
    @RequestMapping("/register")
    public String inputBabysitterInfo(Model model) {
        return "babysitter/babysitterRegister";
    }

    /*
     * register the information of a babysitter
     */
    @RequestMapping("/registerBabysitter")
    public String registerBabysitter(Babysitter babysitter, Model model) {
        // model.addAttribute(babysitterService.createBabysitter(babysitter));
        return "/login";
    }

    /*
     * show the list of babysitters
     */
    @RequestMapping("/listOfBabysitters")
    public String getListBabysitters(Model model) {
        // model.addAttribute(babysitterService.getAllBabysitters());
        return "babysitter/listOfBabysitters";
    }

    /*
     * show the detail information of a babysitter selected
     */
    @RequestMapping("/detailOfBabysitter")
    public String getBabysitter(Babysitter babysitter, Model model) {
//        model.addAttribute(babysitterService.getBabysitter(babysitter.getSnn()));
        return "babysitter/detailOfBabysitter";
    }
}
