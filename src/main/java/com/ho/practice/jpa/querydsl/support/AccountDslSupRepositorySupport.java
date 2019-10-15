package com.ho.practice.jpa.querydsl.support;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ho.practice.jpa.querydsl.impl.AccountDslCus;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ho.practice.jpa.querydsl.support.QAccountDslSup.accountDslSup;

@Repository
public class AccountDslSupRepositorySupport extends QuerydslRepositorySupport {
    
	private final JPAQueryFactory queryFactory;

    public AccountDslSupRepositorySupport(JPAQueryFactory queryFactory) {
        super(AccountDslCus.class);
        this.queryFactory = queryFactory;
    }

    public List<AccountDslSup> findByGender(String gender) {
        return queryFactory
                .selectFrom(accountDslSup)
                .where(accountDslSup.gender.eq(gender))
                .fetch();
    }

}