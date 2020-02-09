package com.ing.bank.repository;

import com.ing.bank.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ajmal
 */
@Repository
public interface AccountRepository extends JpaRepository< Account, Long> {

    @Query(value = "SELECT account FROM Account account WHERE account.customer.id = :customerId")
    List<Account> findAccountsByCustomerId(@Param("customerId") Long customerId);

}
