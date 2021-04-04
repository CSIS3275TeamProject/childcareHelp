package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Family;
import com.example.childcareHelp.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    /*
     * move to input the information of a family
     */
    @RequestMapping("/register")
    public String inputFamilyInfo(Model model) {
        return "family/familyRegister";
    }

    /*
     * register the information of family
     */
    @RequestMapping("/registerFamily")
    public String registerFamily(Family family, Model model) {
//        model.addAttribute(familyService.createFamily(family));
        return "/login";
    }
}
