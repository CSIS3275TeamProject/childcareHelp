package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;


public interface ContractRepository extends MongoRepository<Contract, Integer> {

    @Query("{'familyID': ?0}")
    Collection<Contract> findAllByFamilyId(long familyId);

    @Query("{'snn': ?0}")
    Collection<Contract> findAllByBabysitterId(Integer snn);

    @Query("{'familyID': ?0, 'snn': ?1}")
    Collection<Contract> findAllByFamilyIdNBabysitterId(long familyId, Integer snn);

    @Query("{'contractID': ?0}")
    Contract findAllByContractId(long contractID);
}
