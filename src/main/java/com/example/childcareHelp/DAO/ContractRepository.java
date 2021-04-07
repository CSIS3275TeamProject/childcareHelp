package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Contract;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;


public interface ContractRepository extends MongoRepository<Contract, Integer> {

    @Query("{'familyID': ?0}")
    List<Contract> findAllByFamilyId(long familyId);

    Contract findContractByContractID(long contractId);

    @Query("{'$and' : [{'familyID': ?0},{'contractTitle' : { $regex: ?1 }},{'status': ?2}]}")
    List<Contract> findAllByConditionForFamily(long familyId, String contractTitle, String status);

    @Query("{'$and' : [{'familyID': ?0},{'status': ?1}]}")
    List<Contract> findAllByStatusForFamily(long familyId, String status);

    @Query("{'$and' : [{'familyID': ?0},{'contractTitle' : { $regex: ?1 }}]}")
    List<Contract> findAllByTitleForFamily(long familyId, String contractTitle);

    @Query("{'$and' : [{'snn': ?0},{'contractTitle' : { $regex: ?1 }},{'status': ?2}]}")
    List<Contract> findAllByConditionForBabysitter(long snn, String contractTitle, String status);

    @Query("{'$and' : [{'snn': ?0},{'status': ?1}]}")
    List<Contract> findAllByStatusForBabysitter(long snn, String status);

    @Query("{'$and' : [{'snn': ?0},{'contractTitle' : { $regex: ?1 }}]}")
    List<Contract> findAllByTitleForBabysitter(long snn, String contractTitle);


    @Query("{'snn': ?0}")
    List<Contract> findAllByBabysitterId(long snn);

}
