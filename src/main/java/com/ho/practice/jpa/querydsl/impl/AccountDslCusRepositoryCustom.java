package com.ho.practice.jpa.querydsl.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountDslCusRepositoryCustom {
	
	List<AccountDslCus> findByGender(String gender);

	List<AccountDslCus> findDynamicQuery(String username, String gender);
	
	List<AccountDslCus> findDynamicQueryPage(String username, String gender, Pageable pageable);
	
	List<AccountDslCus> findDynamicQueryAdvance(String username, String gender);
	
	List<AccountDslCus> findDynamicQueryAdvancePage(String username, String gender, Pageable pageable);

	Page<AccountDslCus> findDynamicQueryAdvancePageable(String username, String gender, Pageable pageable);
	
}
