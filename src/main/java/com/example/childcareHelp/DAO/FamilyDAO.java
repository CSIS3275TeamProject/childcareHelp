package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Component
public class FamilyDAO {

    @Autowired
    private FamilyRepository familyRepository;

    public Optional<Family> getFamilyById(Integer familyId) {
        return familyRepository.findById(familyId);
    }

    public Family getFamilyByEmail(String email) {
        return familyRepository.findByEmail(email);
    }

    public Family createFamily(Family family){
        return familyRepository.insert(family);
    }
}

