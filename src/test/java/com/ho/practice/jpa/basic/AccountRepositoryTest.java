package com.ho.practice.jpa.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.ho.practice.jpa.config.JpaAuditingConfig;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories("com.ho.practice.jpa.basic")
@Import({JpaAuditingConfig.class})
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
	
	@Test
	public void 계정추가_시간확인() {
		//given
		Account newAccount = Account.builder().username("홍길동").build();
		
		//when
		accountRepository.save(newAccount);
		
		//then
		assertThat(newAccount).isNotNull();
		assertThat(newAccount.getUsername()).isEqualTo("홍길동");
		assertThat(newAccount.getCreateTime()).isNotNull();
		assertThat(newAccount.getModifyTime()).isNotNull();
	}
	
	@Test
	public void 계정수정_시간확인_수정시간Null() {
		//given
		Account newAccount = Account.builder().username("홍길동").build();
		
		//when
		accountRepository.save(newAccount);
		
		Account modifyAccount = accountRepository.save(
				Account.builder()
					.accountId(newAccount.getAccountId())
					.username("홍길동2")
					.build()
		);
		
		Account findModifyAccount = accountRepository.findById(modifyAccount.getAccountId()).get();
		
		//then
		assertThat(modifyAccount).isNotNull();
		assertThat(modifyAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(modifyAccount.getCreateTime()).isEqualTo(newAccount.getCreateTime());
		assertThat(modifyAccount.getModifyTime()).isNull();
		
		assertThat(findModifyAccount).isNotNull();
		assertThat(findModifyAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(findModifyAccount.getCreateTime()).isEqualTo(newAccount.getCreateTime());
		assertThat(findModifyAccount.getModifyTime()).isNull();
	}
	
	@Test
	public void 계정수정_조회해서_시간확인() {
		//given
		Account newAccount = Account.builder().username("홍길동").build();
		
		//when
		accountRepository.save(newAccount);
		
		Account modifyAccount = accountRepository.save(
				Account.builder()
					.accountId(newAccount.getAccountId())
					.username("홍길동2")
					.build()
		);
		
		Account findModifyAccount = accountRepository.findByAccountId(modifyAccount.getAccountId()).get();
		
		//then
		assertThat(modifyAccount).isNotNull();
		assertThat(modifyAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(modifyAccount.getCreateTime()).isEqualTo(newAccount.getCreateTime());
		assertThat(modifyAccount.getModifyTime()).isNotNull();
		
		assertThat(findModifyAccount).isNotNull();
		assertThat(findModifyAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(findModifyAccount.getCreateTime()).isEqualTo(newAccount.getCreateTime());
		assertThat(findModifyAccount.getModifyTime()).isNotNull();
	}
	
	@Test
	public void 계정수정_시간확인() {
		//given
		Account newAccount = Account.builder().username("홍길동").build();
		
		//when
		accountRepository.save(newAccount);
		
		Account modifyAccount = accountRepository.save(
				Account.builder()
					.accountId(newAccount.getAccountId())
					.username("홍길동2")
					.build()
		);
		accountRepository.flush();
		
		//then
		assertThat(modifyAccount).isNotNull();
		assertThat(modifyAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(modifyAccount.getCreateTime()).isEqualTo(newAccount.getCreateTime());
		assertThat(modifyAccount.getModifyTime()).isNotNull();
	}
	
}
