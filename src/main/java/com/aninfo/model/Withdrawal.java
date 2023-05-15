package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Withdrawal extends Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long number;

    public Withdrawal() {}

    public Withdrawal(Long cbu, Double sum) {
        super(cbu, sum);
    }

    public Long getNumber() {
        return this.number;
    }

    public String getType() {
        return "Withdrawal";
    }
}
