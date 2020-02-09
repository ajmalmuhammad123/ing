package com.ing.bank.service;

import com.ing.bank.model.AccountTypes;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface AccountTypeService {

    public List<AccountTypes> getAccountTypes(Long customerId);

}
