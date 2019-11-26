package com.ho.practice.jpa.querydsl.impl;

import static com.ho.practice.jpa.querydsl.impl.QAccountDslCus.accountDslCus;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
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
    public List<AccountDslCus> findDynamicQueryPage(AccountDslCus param, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(param.getUsername())) {
            builder.and(accountDslCus.username.eq(param.getUsername()));
        }
        if (!StringUtils.isEmpty(param.getGender())) {
            builder.and(accountDslCus.gender.eq(param.getGender()));
        }

        /*
         * orderby 추가 전 로직
         */
//        return queryFactory
//                .selectFrom(accountDslCus)
//                .where(builder)
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
        
        
        
        JPAQuery<AccountDslCus> query = queryFactory
                .selectFrom(accountDslCus)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
                
//        query.orderBy(accountDslCus.username.desc());
        
//        OrderSpecifier<String> orderSpecifier = accountDslCus.username.desc();
//        query.orderBy(orderSpecifier);
      
//	      OrderSpecifier<String> orderSpecifier = new OrderSpecifier<String>(Order.DESC, accountDslCus.username);
//	      query.orderBy(orderSpecifier);
      
//	      OrderSpecifier<String> orderSpecifier = new OrderSpecifier<String>(Order.DESC, Expressions.path(String.class, "username"));
//	      query.orderBy(orderSpecifier);
      
//	      for (org.springframework.data.domain.Sort.Order o : pageable.getSort()) {
//	      	OrderSpecifier<String> orderSpecifier = new OrderSpecifier<String>(
//	      					o.isAscending()?Order.ASC:Order.DESC
//	      					, Expressions.path(String.class, "username"));
//	      	query.orderBy(orderSpecifier);
//	      }
      
	      for (org.springframework.data.domain.Sort.Order o : pageable.getSort()) {
	      	Path<AccountDslCus> path = Expressions.path(AccountDslCus.class, "accountDslCus");
	
	      	@SuppressWarnings({ "rawtypes", "unchecked" })
	      	OrderSpecifier orderSpecifier = new OrderSpecifier(
	      			o.isAscending()?Order.ASC:Order.DESC
	      			, Expressions.path(Object.class, path, o.getProperty()));
	      	query.orderBy(orderSpecifier);
	      }
	      
	      QueryResults<AccountDslCus> result = query.fetchResults();
	      
	      return result.getResults();
    }
    
    @Override
    public List<AccountDslCus> findDynamicQueryAdvance(String username, String gender) {
        return queryFactory
                .selectFrom(accountDslCus)
                .where(eqUserName(username),
                        eqGender(gender))
                .fetch();
    }

    @Override
	public List<AccountDslCus> findDynamicQueryAdvancePage(String username, String gender, Pageable pageable) {
    	return queryFactory
                .selectFrom(accountDslCus)
                .where(eqUserName(username),
                        eqGender(gender))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
	}

	@Override
	public Page<AccountDslCus> findDynamicQueryAdvancePageable(String username, String gender, Pageable pageable) {
		QueryResults<AccountDslCus	> result = queryFactory
                .selectFrom(accountDslCus)
                .where(eqUserName(username),
                        eqGender(gender))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
		
		return new PageImpl<>(result.getResults(), pageable, result.getTotal());
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
