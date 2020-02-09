package com.ing.bank.service;

import com.ing.bank.forms.CustomerForm;
import com.ing.bank.model.Customer;
import com.ing.bank.view.CustomerView;
import java.text.ParseException;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author muhammad.ajmal
 */
public interface CustomerService extends UserDetailsService {

    public Customer findByEmail(String email);

    public CustomerView save(CustomerForm registrationForm) throws ParseException;

}
