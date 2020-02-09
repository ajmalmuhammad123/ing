package com.ing.bank.service;

import com.ing.bank.forms.AccountForm;
import com.ing.bank.model.Account;
import com.ing.bank.model.AccountTypes;
import com.ing.bank.model.Customer;
import com.ing.bank.repository.AccountRepository;
import com.ing.bank.repository.AccountTypesRepository;
import com.ing.bank.repository.CustomerRepository;
import com.ing.bank.view.AccountView;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final String DEFAULT_ACCOUNT = "SAVINGS";
    private static final Long DEFAULT_BALANCE = 0l;
    private static final Integer DEFAULT_INTEREST = 3;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTypesRepository accountTypesRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AccountView save(AccountForm accountForm) {
        Optional<Customer> customer = customerRepository.findById(accountForm.getCustomerId());
        if (!customer.isPresent()) {
            return null;
        }
        AccountTypes accountTypes = accountTypesRepository.findAccountTypesByName(DEFAULT_ACCOUNT);
        Account account = new Account();
        BeanUtils.copyProperties(accountForm, account);
        account.setAccountType(accountTypes);
        account.setCustomer(customer.get());
        if (account.getBalance() == null) {
            account.setBalance(DEFAULT_BALANCE);
        }
        if (account.getIntRate() == null) {
            account.setIntRate(DEFAULT_INTEREST);
        }
        account = accountRepository.save(account);
        AccountView accountView = new AccountView();
        BeanUtils.copyProperties(account, accountView);
        return accountView;
    }

}
