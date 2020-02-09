package com.ing.bank.repository;

import com.ing.bank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author muhammad.ajmal
 */
@Repository
public interface CustomerRepository extends JpaRepository< Customer, Long> {

    Customer findByEmail(String email);
}
