package com.alayon.msbank.withdrawal.services;

import com.alayon.msbank.withdrawal.models.TransactionModel;
import com.alayon.msbank.withdrawal.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService  implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionModel add(TransactionModel transactionModel) {
        return transactionRepository.save(transactionModel);
    }

}
