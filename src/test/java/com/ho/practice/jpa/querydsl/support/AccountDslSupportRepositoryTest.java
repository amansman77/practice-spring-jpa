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

import com.ho.practice.jpa.querydsl.support.AccountDslSup;
import com.ho.practice.jpa.querydsl.support.AccountDslSupRepository;
import com.ho.practice.jpa.querydsl.support.AccountDslSupRepositorySupport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDslSupportRepositoryTest {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	AccountDslSupRepository accountDynamicQueryRepository;
	
	@Autowired
	AccountDslSupRepositorySupport accountDynamicQueryRepositorySupport;

	@Test
	public void pageTest() throws SQLException {
		//given
		List<AccountDslSup> list = 
				  Arrays.asList(new AccountDslSup(1L, "user1", "userpass1", "m")
						  , new AccountDslSup(2L, "user2", "userpass2", "m")
						  , new AccountDslSup(3L, "user3", "userpass3", "m")
						  , new AccountDslSup(4L, "user4", "userpass4", "m")
						  , new AccountDslSup(5L, "user5", "userpass5", "m")
						  , new AccountDslSup(6L, "user6", "userpass6", "w")
						  , new AccountDslSup(7L, "user7", "userpass7", "w")
						  , new AccountDslSup(8L, "user8", "userpass8", "w")
						  , new AccountDslSup(9L, "user9", "userpass9", "w")
						  , new AccountDslSup(10L, "user10", "userpass10", "w")
				                );
		
		accountDynamicQueryRepository.saveAll(list);
		
		//when
		List<AccountDslSup> manList = accountDynamicQueryRepositorySupport.findByGender("m");
		
		//then
		assertThat(manList).isNotNull();
		assertThat(manList.size()).isEqualTo(5);
		
	}
	
}
