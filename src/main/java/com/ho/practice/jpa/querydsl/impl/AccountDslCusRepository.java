package com.ho.practice.jpa.querydsl.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDslCusRepository extends JpaRepository<AccountDslCus, Long>, AccountDslCusRepositoryCustom {
}
