package com.alayon.msbank.deposit.services;

import com.alayon.msbank.deposit.models.TransactionModel;

public interface ITransactionService {
    public TransactionModel add (TransactionModel transactionModel);
}
