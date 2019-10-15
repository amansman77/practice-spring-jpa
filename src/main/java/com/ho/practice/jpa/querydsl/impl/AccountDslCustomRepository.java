package com.ho.practice.jpa.querydsl.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDslCustomRepository extends JpaRepository<AccountDslCustrom, Long>, AccountDslCustomRepositoryCustom {
}
