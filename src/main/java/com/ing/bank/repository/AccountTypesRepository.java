package com.ing.bank.repository;

import com.ing.bank.model.AccountTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ajmal
 */
public interface AccountTypesRepository extends JpaRepository< AccountTypes, Long> {

    @Query(value = "SELECT account FROM AccountTypes account WHERE account.name = :name")
    AccountTypes findAccountTypesByName(@Param("name") String name);
}
