package com.aninfo.service;

import com.aninfo.exceptions.WithdrawNegativeSumException;
import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Deposit;
import com.aninfo.model.Transaction;
import com.aninfo.model.Withdrawal;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Collection<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Collection<Transaction> getTransactionsByCbu(Long cbu) {
        List<Transaction> transactions = transactionRepository.findAll();
        List<Transaction> transactionsCbu = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.hasCbu(cbu)) {
                transactionsCbu.add(t);
            }
        }
        return transactionsCbu;
    }

    public Optional<Transaction> findTransactionById(Long numero) {
        return transactionRepository.findById(numero);
    }

    public Optional<Account> findAccountById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccountById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    public void deleteTransactionById(Long numero) {
        transactionRepository.deleteById(numero);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new WithdrawNegativeSumException("Cannot withdraw negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);

        if (!account.canWithdraw(sum)) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        Withdrawal withdrawal = account.withdraw(sum);
        accountRepository.save(account);

        transactionRepository.save(withdrawal);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        Deposit deposit = account.deposit(sum);
        accountRepository.save(account);

        transactionRepository.save(deposit);

        return account;
    }
}
