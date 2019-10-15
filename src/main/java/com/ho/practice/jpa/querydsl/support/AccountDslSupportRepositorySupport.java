package com.ho.practice.jpa.querydsl.support;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.ho.practice.jpa.querydsl.impl.AccountDslCustrom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.ho.practice.jpa.querydsl.support.QAccountDslSupport.accountDslSupport;

@Repository
public class AccountDslSupportRepositorySupport extends QuerydslRepositorySupport {
    
	private final JPAQueryFactory queryFactory;

    public AccountDslSupportRepositorySupport(JPAQueryFactory queryFactory) {
        super(AccountDslCustrom.class);
        this.queryFactory = queryFactory;
    }

    public List<AccountDslSupport> findByGender(String gender) {
        return queryFactory
                .selectFrom(accountDslSupport)
                .where(accountDslSupport.gender.eq(gender))
                .fetch();
    }

}