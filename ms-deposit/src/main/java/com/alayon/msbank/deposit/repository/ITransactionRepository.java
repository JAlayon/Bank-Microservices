package com.alayon.msbank.deposit.repository;

import com.alayon.msbank.deposit.models.TransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends CrudRepository<TransactionModel, Long>{

}