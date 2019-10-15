package com.ho.practice.jpa.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	AccountRepository accountRepository;

	@Test
	public void accountTest() throws SQLException {
		Account account = new Account();
		account.setUsername("testuser");
		account.setPassword("testpass");
	
		Account newAccount = accountRepository.save(account);
	
		assertThat(newAccount).isNotNull();
	
		Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
		assertThat(existingAccount).isNotNull();
	
		Account nonExistingAccount = accountRepository.findByUsername("superman");
		assertThat(nonExistingAccount).isNull();
	}
	
}
