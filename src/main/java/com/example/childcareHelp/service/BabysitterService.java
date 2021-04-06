package com.example.childcareHelp.service;

import com.example.childcareHelp.DAO.BabysitterDAO;
import com.example.childcareHelp.entity.Babysitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class
BabysitterService {

    @Autowired
    private BabysitterDAO babysitterDAO;

    public boolean checkExistedBabysitter(String email) {
        Babysitter babysitter = babysitterDAO.getBabysitterByEmail(email);
        if (babysitter == null) {
            return false;
        } else {
            return true;
        }
    }

    public List<Babysitter> getAllBabysitters() {
        return babysitterDAO.getAllBabysitters();
    }

    public Optional<Babysitter> getBabysitter(long snn) {
        return babysitterDAO.getBabysitterBySnn(snn);
    }

    public Babysitter createBabysitter(Babysitter babysitter) {
        System.out.println("[LOG]_BabysitterService_createrBabysitter_");
        return babysitterDAO.createBabysitter(babysitter);
    }
}
