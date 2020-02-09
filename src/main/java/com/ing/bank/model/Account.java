package com.ing.bank.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author muhammad.ajmal
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_type", nullable = false)
    private AccountTypes accountTypes;
    private Long balance;
    private Integer intRate;

    public Account() {
    }

    public Account(Long id, Customer customer, AccountTypes accountType, Long balance, int intRate) {
        this.id = id;
        this.customer = customer;
        this.accountTypes = accountType;
        this.balance = balance;
        this.intRate = intRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountTypes getAccountType() {
        return accountTypes;
    }

    public void setAccountType(AccountTypes accountType) {
        this.accountTypes = accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Integer getIntRate() {
        return intRate;
    }

    public void setIntRate(Integer intRate) {
        this.intRate = intRate;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", customer=" + customer + ", accountTypes=" + accountTypes + ", balance=" + balance + ", intRate=" + intRate + '}';
    }

}
