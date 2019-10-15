package com.ho.practice.jpa.querydsl.support;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ho.practice.jpa.querydsl.support.AccountDslSupport;
import com.ho.practice.jpa.querydsl.support.AccountDslSupportRepository;
import com.ho.practice.jpa.querydsl.support.AccountDslSupportRepositorySupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDslSupportRepositoryTest {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	AccountDslSupportRepository accountDynamicQueryRepository;
	
	@Autowired
	AccountDslSupportRepositorySupport accountDynamicQueryRepositorySupport;

	@Test
	public void pageTest() throws SQLException {
		//given
		List<AccountDslSupport> list = 
				  Arrays.asList(new AccountDslSupport(1L, "user1", "userpass1", "m")
						  , new AccountDslSupport(2L, "user2", "userpass2", "m")
						  , new AccountDslSupport(3L, "user3", "userpass3", "m")
						  , new AccountDslSupport(4L, "user4", "userpass4", "m")
						  , new AccountDslSupport(5L, "user5", "userpass5", "m")
						  , new AccountDslSupport(6L, "user6", "userpass6", "w")
						  , new AccountDslSupport(7L, "user7", "userpass7", "w")
						  , new AccountDslSupport(8L, "user8", "userpass8", "w")
						  , new AccountDslSupport(9L, "user9", "userpass9", "w")
						  , new AccountDslSupport(10L, "user10", "userpass10", "w")
				                );
		
		accountDynamicQueryRepository.saveAll(list);
		
		//when
		List<AccountDslSupport> manList = accountDynamicQueryRepositorySupport.findByGender("m");
		
		//then
		assertThat(manList).isNotNull();
		assertThat(manList.size()).isEqualTo(5);
		
	}
	
}
