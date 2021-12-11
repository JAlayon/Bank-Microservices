package com.alayon.msbank.withdrawal.repository;

import com.alayon.msbank.withdrawal.models.TransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends CrudRepository<TransactionModel, Long>{

}
