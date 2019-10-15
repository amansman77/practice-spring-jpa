package com.ho.practice.jpa.querydsl.impl;

import static com.ho.practice.jpa.querydsl.impl.QAccountDslCus.accountDslCus;

import java.util.List;

import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountDslCusRepositoryImpl implements AccountDslCusRepositoryCustom {

	private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountDslCus> findByGender(String gender) {
        return queryFactory.selectFrom(accountDslCus)
                .where(accountDslCus.gender.eq(gender))
                .fetch();
    }

    @Override
    public List<AccountDslCus> findDynamicQuery(String username, String gender) {

        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(username)) {
            builder.and(accountDslCus.username.eq(username));
        }
        if (!StringUtils.isEmpty(gender)) {
            builder.and(accountDslCus.gender.eq(gender));
        }

        return queryFactory
                .selectFrom(accountDslCus)
                .where(builder)
                .fetch();
    }
    
    @Override
    public List<AccountDslCus> findDynamicQueryAdvance(String username, String gender) {
        return queryFactory
                .selectFrom(accountDslCus)
                .where(eqUserName(username),
                        eqGender(gender))
                .fetch();
    }

    private BooleanExpression eqUserName(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return accountDslCus.username.eq(username);
    }

    private BooleanExpression eqGender(String gender) {
        if (StringUtils.isEmpty(gender)) {
            return null;
        }
        return accountDslCus.gender.eq(gender);
    }

}
