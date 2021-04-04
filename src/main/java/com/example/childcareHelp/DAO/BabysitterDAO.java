package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Babysitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class BabysitterDAO {

    @Autowired
    private BabysitterRepository babysitterRepository;

    public Collection<Babysitter> getAllBabysitters() {

        return babysitterRepository.findAll();
    }


    public Collection<Babysitter> getBabysitterByCondition(Babysitter babysitter) {
        return babysitterRepository.findBabysitterByCondition(babysitter);
    }

    public Optional<Babysitter> getBabysitterBySnn(Integer snn) {
        return babysitterRepository.findById(snn);
    }
    public Babysitter getBabysitter(String email, String password) {
        return babysitterRepository.findByEmailAndPassword(email, password);
    }

    public Babysitter createBabysitter(Babysitter babysitter){
        System.out.println("[LOG]_BabysitterDAO_createrBabysitter_");
        return babysitterRepository.insert(babysitter);
    }
}

