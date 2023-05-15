package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long number;

    protected Long cbu;

    protected Double sum;

    public Transaction() {}

    public Transaction(Long cbu, Double sum) {
        this.cbu = cbu;
        this.sum = sum;
    }

    public Long getCbu() {
        return this.cbu;
    }

    public Double getSum() {
        return this.sum;
    }

    public boolean hasCbu(Long cbu) {
        return Objects.equals(this.cbu, cbu);
    }
}
