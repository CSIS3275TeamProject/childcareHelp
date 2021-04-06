package com.example.childcareHelp.service;

import com.example.childcareHelp.DAO.BabysitterDAO;
import com.example.childcareHelp.DAO.FamilyDAO;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    private FamilyDAO familyDAO;
    @Autowired
    private BabysitterDAO babysitterDAO;

    public Family doLoginForFamily(String email, String password) {

        return familyDAO.getFamilyByEmail(email);
    }

    public Babysitter doLoginForBabysitter(String email, String password) {

        return babysitterDAO.getBabysitter(email, password);
    }

}
