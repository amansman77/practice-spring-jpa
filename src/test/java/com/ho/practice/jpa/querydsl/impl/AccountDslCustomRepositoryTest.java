package com.ho.practice.jpa.querydsl.impl;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDslCustomRepositoryTest {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	AccountDslCustomRepository accountDslCustomRepository;
	
	@Test
	public void pageTest() throws SQLException {
		//given
		List<AccountDslCustrom> list = 
				  Arrays.asList(new AccountDslCustrom(1L, "user1", "userpass1", "m")
						  , new AccountDslCustrom(2L, "user2", "userpass2", "m")
						  , new AccountDslCustrom(3L, "user3", "userpass3", "m")
						  , new AccountDslCustrom(4L, "user4", "userpass4", "m")
						  , new AccountDslCustrom(5L, "user5", "userpass5", "m")
						  , new AccountDslCustrom(6L, "user6", "userpass6", "w")
						  , new AccountDslCustrom(7L, "user7", "userpass7", "w")
						  , new AccountDslCustrom(8L, "user8", "userpass8", "w")
						  , new AccountDslCustrom(9L, "user9", "userpass9", "w")
						  , new AccountDslCustrom(10L, "user10", "userpass10", "w")
				                );
		
		accountDslCustomRepository.saveAll(list);
		
		//when
		List<AccountDslCustrom> manList = accountDslCustomRepository.findByGender("m");
		
		//then
		assertThat(manList).isNotNull();
		assertThat(manList.size()).isEqualTo(5);
		
	}
	
}
