package com.ho.practice.jpa.querydsl.impl;

import java.util.List;

public interface AccountDslCusRepositoryCustom {
	
	List<AccountDslCus> findByGender(String gender);

	List<AccountDslCus> findDynamicQuery(String username, String gender);
	
	List<AccountDslCus> findDynamicQueryAdvance(String username, String gender);

}
