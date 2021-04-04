package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.service.BabysitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/babysitter")
public class BabysitterController {

    @Autowired
    private BabysitterService babysitterService;

    /* testtesttesttesttest
     * move to input the information of a babysitter
     */
    @GetMapping("/register")
    public String inputBabysitterInfo(@ModelAttribute("babysitter")  Babysitter babysitter, Model model) {
        return "/babysitter/babysitterRegister";
    }

    /*
     * register the information of a babysitter
     */
    @PostMapping("/registerBabysitter")
    public String registerBabysitter(@ModelAttribute("babysitter")  Babysitter babysitter,
                                     @ModelAttribute("loginInfo") LoginInfo loginInfo,  Model model) {

        model.addAttribute("babysitter", babysitterService.createBabysitter(babysitter));
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
