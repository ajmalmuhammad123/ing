package com.ing.bank.view;

import java.util.HashSet;
import java.util.Set;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ajmal
 */
public class CustomerView {

    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Set<AccountView> accounts;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<AccountView> getAccounts() {
        if (CollectionUtils.isEmpty(accounts)) {
            accounts = new HashSet<>();
        }
        return accounts;
    }

    public void setAccounts(Set<AccountView> accounts) {
        this.accounts = accounts;
    }

}
