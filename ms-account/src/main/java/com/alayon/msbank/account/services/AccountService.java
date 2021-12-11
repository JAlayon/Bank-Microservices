package com.alayon.msbank.account.services;

import com.alayon.msbank.account.models.AccountModel;
import com.alayon.msbank.account.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepository accountRepository;

    @Override
    public List<AccountModel> findAll() {
        return  (List<AccountModel>)accountRepository.findAll();
    }

    @Override
    public AccountModel update(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }

    @Override
    public AccountModel findById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

}
