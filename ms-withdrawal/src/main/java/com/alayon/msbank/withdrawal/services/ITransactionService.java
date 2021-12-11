package com.alayon.msbank.withdrawal.services;

import com.alayon.msbank.withdrawal.models.TransactionModel;

public interface ITransactionService {
    public TransactionModel add (TransactionModel transactionModel);
}
