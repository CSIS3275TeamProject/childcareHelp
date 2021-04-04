package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Babysitter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.Optional;


public interface BabysitterRepository extends MongoRepository<Babysitter, Integer> {
    public default Babysitter findByEmail(String email) {
        return null;
    }

    public default Optional<Babysitter> findById(Integer snn) {
        return null;
    }

    @Query("{'email': ?0, 'password':?1 }")
    Babysitter findByEmailAndPassword(String email, String password);


    public default Collection<Babysitter> findBabysitterByCondition(Babysitter babysitter) {
        return null;
    }

}
