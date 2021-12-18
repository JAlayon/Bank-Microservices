package com.alayon.msbank.withdrawal.controller;

import com.alayon.msbank.withdrawal.dtos.TransactionRequest;
import com.alayon.msbank.withdrawal.messages.TransactionMessagePublish;
import com.alayon.msbank.withdrawal.models.TransactionModel;
import com.alayon.msbank.withdrawal.services.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    ITransactionService transactionService;

    @Autowired
    TransactionMessagePublish messageEvent;

    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdrawal(@RequestBody TransactionRequest request) throws Exception {

        logger.info("Post: AccountId {} - Ammount {}", request.getAccountId(), request.getAmount());
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAccountId(request.getAccountId());
        transactionModel.setAmount(request.getAmount());
        transactionModel.setType("withdrawal");
        transactionModel = transactionService.add(transactionModel);

        messageEvent.sendDepositEvent(transactionModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionModel);
    }
}
