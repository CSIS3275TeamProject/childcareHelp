package com.example.childcareHelp.DAO;


import com.example.childcareHelp.entity.Family;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;


public interface FamilyRepository extends MongoRepository<Family, Integer> {
    public Family findByFamilyID(long familyId);
    public Family findByEmail(String email);
    public default List<Family> findFamilyByCondition(Family family) {
        return null;
    }
}
