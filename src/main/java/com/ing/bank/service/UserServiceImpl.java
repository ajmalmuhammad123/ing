package com.ing.bank.service;

import com.ing.bank.forms.CustomerRegistrationForm;
import com.ing.bank.model.Account;

import com.ing.bank.model.Customer;
import com.ing.bank.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author muhammad.ajmal
 */
@Service
public class UserServiceImpl implements CustomerService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public Customer findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Customer save(CustomerRegistrationForm registrationForm) {
    Customer user = new Customer();
    user.setFirstName(registrationForm.getFirstName());
    user.setLastName(registrationForm.getLastName());
    user.setEmail(registrationForm.getEmail());
    user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
    user.setRoles(Arrays.asList(new Account("ROLE_USER")));
    return userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Customer user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
  }

  private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection< Account> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
  }
}
