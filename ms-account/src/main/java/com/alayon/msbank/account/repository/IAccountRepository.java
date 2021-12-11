package com.alayon.msbank.account.repository;

import com.alayon.msbank.account.models.AccountModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<AccountModel,Integer> {

}
