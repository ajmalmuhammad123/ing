package com.ing.bank.service;

import com.ing.bank.enums.UserStatus;
import com.ing.bank.forms.CustomerForm;
import com.ing.bank.model.AccountTypes;

import com.ing.bank.model.Customer;
import com.ing.bank.view.CustomerView;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ing.bank.repository.CustomerRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author muhammad.ajmal
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository userRepository;

    @Autowired
    private AccountTypeService accountTypeService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public CustomerView save(CustomerForm customerForm) throws ParseException {
        Customer customer = new Customer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BeanUtils.copyProperties(customerForm, customer);
        customer.setPassword(passwordEncoder.encode(customerForm.getPassword()));
        customer.setDateOfBirth(sdf.parse(customerForm.getDateOfBirth()));
        customer.setStatus(UserStatus.ACTIVE.getValue());
        customer.setLastLogin(new Date());
        customer = userRepository.save(customer);
        CustomerView customerView = new CustomerView();
        BeanUtils.copyProperties(customer, customerView);
        customerView.setCustomerId(customer.getId());
        return customerView;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                getAccountTypes(user.getId()));
    }

    private Collection< ? extends GrantedAuthority> getAccountTypes(Long customerId) {
        List<AccountTypes> accountTypes = accountTypeService.getAccountTypes(customerId);
        return accountTypes.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
