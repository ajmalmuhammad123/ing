package com.ing.bank.service;

import com.ing.bank.forms.CustomerRegistrationForm;
import com.ing.bank.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author muhammad.ajmal
 */
public interface CustomerService extends UserDetailsService {

  Customer findByEmail(String email);

  Customer save(CustomerRegistrationForm registrationForm);

}
