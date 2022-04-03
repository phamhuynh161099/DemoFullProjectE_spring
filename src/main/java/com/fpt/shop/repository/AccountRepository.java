package com.fpt.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.shop.domain.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
