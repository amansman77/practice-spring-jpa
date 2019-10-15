package com.ho.practice.jpa.basic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUsername(String username);
	
}
