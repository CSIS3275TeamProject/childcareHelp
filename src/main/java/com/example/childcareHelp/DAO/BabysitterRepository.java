package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Babysitter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface BabysitterRepository extends MongoRepository<Babysitter, Integer> {


    public Babysitter findByEmail(String email);

    @Query("{'snn': ?0}")
    Optional<Babysitter> findById(long snn);

    @Query("{'email': ?0, 'password':?1 }")
    Babysitter findByEmailAndPassword(String email, String password);


    public default Collection<Babysitter> findBabysitterByCondition(Babysitter babysitter) {
        return null;
    }

    @Query("{'name': ?0, 'gender': ?1, 'highestEducation': ?2}")
    public List<Babysitter> findByCondition(String input, String gender, String degree, String age);

}
