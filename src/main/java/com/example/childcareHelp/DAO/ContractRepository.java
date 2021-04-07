package com.example.childcareHelp.DAO;

import com.example.childcareHelp.entity.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;


public interface ContractRepository extends MongoRepository<Contract, Integer> {

    @Query("{'familyID': ?0}")
    List<Contract> findAllByFamilyId(long familyId);

//    @Query("{'contractId': ?0}")
    Contract findContractByContractID(long contractId);

    @Query("{'contractId': ?0}, {$set : {status : $1}}")
    Contract updateContract(long contractId, String status);


    //    @Query("{'$and' : [{'familyID': ?0},{'status': ?1},{'startDate': {$gt : ?2}},{'endDate': {$lt : ?2}}]}")
    @Query("{'$or' : [{'familyID': ?0},{'status': ?1}]}")
    List<Contract> findAllByCondition(long familyId, String status, String yyyyMM);

    @Query("{'snn': ?0}")
    Collection<Contract> findAllByBabysitterId(long snn);

    @Query("{'familyID': ?0, 'snn': ?1}")
    Collection<Contract> findAllByFamilyIdNBabysitterId(long familyId, Integer snn);

    @Query("{'contractID': ?0}")
    Contract findAllByContractId(long contractID);
}
