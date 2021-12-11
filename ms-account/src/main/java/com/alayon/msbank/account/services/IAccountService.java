package com.alayon.msbank.account.services;


import com.alayon.msbank.account.models.AccountModel;

import java.util.List;


public interface IAccountService {
    List<AccountModel> findAll();
    AccountModel findById (Integer id);
    AccountModel update(AccountModel accountModel);
}
