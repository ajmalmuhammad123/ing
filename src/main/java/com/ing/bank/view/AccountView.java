package com.ing.bank.view;

/**
 *
 * @author ajmal
 */
public class AccountView {

    private Long customerId;

    private AccountTypeView accountType;

    private Long balance;

    private int interest;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public AccountTypeView getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeView accountType) {
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
