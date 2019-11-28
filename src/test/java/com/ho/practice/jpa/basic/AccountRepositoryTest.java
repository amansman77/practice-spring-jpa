package com.ho.practice.jpa.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.time.LocalDateTime;

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
		/*
		 * Case 1
		 * 데이터 추가
		 */
		Account newAccount = Account.builder().username("홍길동").build();
		accountRepository.save(newAccount);
		
		assertThat(newAccount).isNotNull();
		assertThat(newAccount.getUsername()).isEqualTo("홍길동");
		assertThat(newAccount.getCreateTime()).isNotNull();
		assertThat(newAccount.getModifyTime()).isNotNull();
		
		LocalDateTime beforeCreateTime = newAccount.getCreateTime();
//		LocalDateTime beforeModifyTime = newAccount.getModifyTime();
		
		/*
		 * Case 2
		 * 데이터 수정
		 */
		newAccount.setUsername("홍길동2");
		
		assertThat(newAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(newAccount.getCreateTime()).isEqualTo(beforeCreateTime);
//		assertThat(newAccount.getModifyTime()).isNotEqualTo(beforeModifyTime);
		
		/*
		 * Case 3
		 * 수정한 데이터 조회
		 */
		Account findModifyAccount = accountRepository.findById(newAccount.getAccountId()).get();
		
		assertThat(findModifyAccount).isNotNull();
		assertThat(findModifyAccount.getUsername()).isEqualTo(newAccount.getUsername());
		assertThat(findModifyAccount.getCreateTime()).isEqualTo(beforeCreateTime);
//		assertThat(findModifyAccount.getModifyTime()).isNotEqualTo(beforeModifyTime);
	}
	
	@Test
	public void 계정수정_조회해서_시간확인() {
		/*
		 * Case 1
		 * 데이터 추가
		 */
		Account newAccount = Account.builder().username("홍길동").build();
		accountRepository.save(newAccount);
		
		assertThat(newAccount).isNotNull();
		assertThat(newAccount.getUsername()).isEqualTo("홍길동");
		assertThat(newAccount.getCreateTime()).isNotNull();
		assertThat(newAccount.getModifyTime()).isNotNull();
		
		LocalDateTime beforeCreateTime = newAccount.getCreateTime();
		LocalDateTime beforeModifyTime = newAccount.getModifyTime();
		
		/*
		 * Case 2
		 * 데이터 수정
		 */
		newAccount.setUsername("홍길동2");
		
		assertThat(newAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(newAccount.getCreateTime()).isEqualTo(beforeCreateTime);
//		assertThat(newAccount.getModifyTime()).isNotEqualTo(beforeModifyTime);
		
		/*
		 * Case 3
		 * 수정한 데이터 조회
		 */
		Account findModifyAccount = accountRepository.findByAccountId(newAccount.getAccountId()).get();
		
		assertThat(findModifyAccount).isNotNull();
		assertThat(findModifyAccount.getUsername()).isEqualTo(newAccount.getUsername());
		assertThat(findModifyAccount.getCreateTime()).isEqualTo(beforeCreateTime);
		assertThat(findModifyAccount.getModifyTime()).isNotEqualTo(beforeModifyTime);
		
		assertThat(newAccount.getModifyTime()).isNotEqualTo(beforeModifyTime);
	}
	
	@Test
	public void 계정수정_시간확인() {
		/*
		 * Case 1
		 * 데이터 추가
		 */
		Account newAccount = Account.builder().username("홍길동").build();
		accountRepository.save(newAccount);
		
		assertThat(newAccount).isNotNull();
		assertThat(newAccount.getUsername()).isEqualTo("홍길동");
		assertThat(newAccount.getCreateTime()).isNotNull();
		assertThat(newAccount.getModifyTime()).isNotNull();
		
		LocalDateTime beforeCreateTime = newAccount.getCreateTime();
		LocalDateTime beforeModifyTime = newAccount.getModifyTime();
		
		/*
		 * Case 2
		 * 데이터 수정
		 */
		newAccount.setUsername("홍길동2");
		accountRepository.flush();
		
		assertThat(newAccount.getUsername()).isEqualTo("홍길동2");
		assertThat(newAccount.getCreateTime()).isEqualTo(beforeCreateTime);
		assertThat(newAccount.getModifyTime()).isNotEqualTo(beforeModifyTime);
	}
	
}
