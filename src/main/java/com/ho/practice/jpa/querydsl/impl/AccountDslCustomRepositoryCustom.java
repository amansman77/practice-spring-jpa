package com.ho.practice.jpa.querydsl.impl;

import java.util.List;

public interface AccountDslCustomRepositoryCustom {
	
	List<AccountDslCustrom> findByGender(String gender);
	
}
