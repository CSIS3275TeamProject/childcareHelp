package com.example.childcareHelp.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.service.BabysitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        List<Babysitter> babysitterList = babysitterService.getAllBabysitters();
        model.addAttribute("babysitters", babysitterList);
        return "babysitter/listOfBabysitters";
    }

    
    

    /*
     * show the detail information of a babysitter selected
     */
    @RequestMapping("/detailOfBabysitter")
    public String getBabysitter(Babysitter babysitter, Model model) {
        Optional<Babysitter> babysitterSelected = babysitterService.getBabysitter(babysitter.getSnn());
        model.addAttribute("babysitter", babysitterSelected);
        return "babysitter/detailOfBabysitter";
    }
}
