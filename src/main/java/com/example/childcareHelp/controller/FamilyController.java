package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Child;
import com.example.childcareHelp.entity.Family;
import com.example.childcareHelp.service.ChildService;
import com.example.childcareHelp.service.FamilyService;
import com.example.childcareHelp.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private ChildService childService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

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
        long newFamilyId = sequenceGeneratorService.generateSequence(Family.SEQUENCE_NAME);
        family.setFamilyID(newFamilyId);
        List<Child> children = family.getChildren();
        Family resultFamily = familyService.createFamily(family);
        for (int i = 0; i < children.size(); i++) {
            Child child = children.get(i);
            child.setChildID(sequenceGeneratorService.generateSequence(Child.SEQUENCE_NAME));
            child.setFamilyID(newFamilyId);
            Child resultChild = childService.createChild(child);
        }

        return "redirect:/login";
    }
}
