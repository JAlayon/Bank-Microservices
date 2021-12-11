package com.alayon.msbank.account.controller;

import com.alayon.msbank.account.metrics.IncreaseCounterAccount;
import com.alayon.msbank.account.models.AccountModel;
import com.alayon.msbank.account.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IncreaseCounterAccount increaseCounterAccount;

    @GetMapping()
    public List<AccountModel> get() {
        increaseCounterAccount.increaseCounter();
        return accountService.findAll();
    }
}

