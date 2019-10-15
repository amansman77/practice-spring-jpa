package com.ho.practice.jpa.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories("com.ho.practice.jpa.basic")
public class AccountRepositoryTest {
	
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
