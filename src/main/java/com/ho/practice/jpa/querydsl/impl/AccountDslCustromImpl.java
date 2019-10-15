package com.ho.practice.jpa.querydsl.impl;

import static com.ho.practice.jpa.querydsl.impl.QAccountDslCustrom.accountDslCustrom;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountDslCustromImpl implements AccountDslCustomRepositoryCustom {

	private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountDslCustrom> findByGender(String gender) {
        return queryFactory.selectFrom(accountDslCustrom)
                .where(accountDslCustrom.gender.eq(gender))
                .fetch();
    }
    
}
