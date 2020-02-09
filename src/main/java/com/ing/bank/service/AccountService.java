package com.ing.bank.service;

import com.ing.bank.forms.AccountForm;
import com.ing.bank.view.AccountView;

/**
 *
 * @author ajmal
 */
public interface AccountService {

    public AccountView save(AccountForm accountForm);

}
