package com.ing.bank.service;

import com.ing.bank.model.Account;
import com.ing.bank.model.AccountTypes;
import com.ing.bank.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountTypes> getAccountTypes(Long customerId) {
        List<AccountTypes> accountTypeses = new ArrayList<>();
        List<Account> accounts = accountRepository.findAccountsByCustomerId(customerId);
        accounts.forEach((account) -> {
            accountTypeses.add(account.getAccountType());
        });
        return accountTypeses;
    }

}
