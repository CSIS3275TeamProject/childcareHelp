package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Babysitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class BabysitterDAO {

    @Autowired
    private BabysitterRepository babysitterRepository;

    public Babysitter getBabysitterByEmail(String email){
        return babysitterRepository.findByEmail(email);
    }

    public List<Babysitter> getAllBabysitters() {
        return babysitterRepository.findAll();
    }

    public Optional<Babysitter> getBabysitterBySnn(long snn) {
        return babysitterRepository.findById(snn);
    }
    public Babysitter getBabysitter(String email, String password) {
        return babysitterRepository.findByEmailAndPassword(email, password);
    }

    public Babysitter createBabysitter(Babysitter babysitter){
        System.out.println("[LOG]_BabysitterDAO_createrBabysitter_");
        return babysitterRepository.insert(babysitter);
    }

    public List<Babysitter> getBabysittersByCondition(String input, String gender, String degree) {
        List<Babysitter> babysitters;
        if(gender.equals(degree)) {
            babysitters = babysitterRepository.findByCondition(input);
        } else if(degree.equals("0")) {
            babysitters = babysitterRepository.findByCondition(input, gender);
        } else if(gender.equals("0")) {
            babysitters = babysitterRepository.findByConditions(input, degree);
        } else {
            babysitters = babysitterRepository.findByCondition(input, gender, degree);
        }
        return babysitters;
    }
}

