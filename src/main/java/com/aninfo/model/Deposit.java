package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deposit extends Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long number;

    public Deposit() {}

    public Deposit(Long cbu, Double sum) {
        super(cbu, sum);
    }

    public Long getNumber() {
        return this.number;
    }

    public String getType() {
        return "Deposit";
    }
}
