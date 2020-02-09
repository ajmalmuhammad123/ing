package com.ing.bank.forms;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author ajmal
 */
public class AccountForm {

    @NotEmpty
    private Long customerId;

    @NotEmpty
    private Long accountType;

    @NotEmpty
    private Long balance;

    @NotEmpty
    private int interest;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountType() {
        return accountType;
    }

    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

}
