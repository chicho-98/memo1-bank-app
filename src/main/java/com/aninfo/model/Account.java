package com.aninfo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Deposit deposit(Double sum) {
        Double sumDeposited;
        if (sum < 2000) {
            sumDeposited = sum;
        } else if (sum < 5000) {
            sumDeposited = sum * 1.1;
        } else {
            sumDeposited = sum + 500;
        }
        this.setBalance(this.balance + sumDeposited);
        return new Deposit(this.cbu, sumDeposited);
    }

    public boolean canWithdraw(Double sum) {
        return this.balance >= sum;
    }

    public Withdrawal withdraw(Double sum) {
        this.setBalance(this.balance - sum);
        return new Withdrawal(this.cbu, sum);
    }

}
