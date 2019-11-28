package com.ho.practice.jpa.basic;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 실제 DB와 연동하여 테스트하는 환경
 * DB에 테스트 데이터가 적용됨
 * 
 * @author hhsung
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceIntegrationTest {

	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Test
	public void sn10_테스트데이터_추가() {
		accountRepository.save(Account.builder().accountId(1L).username("홍길동").build());
	}
	
	@Test
	public void sn20_flush후_롤백() {
		
		try {
			accountService.modify_flush_occursCheckedException("홍길동2");
		} catch (TimeoutException e) {
		}
		
		Optional<Account> account = accountRepository.findByAccountId(1L);
		assertThat(account.get().getUsername()).isEqualTo("홍길동");
	}
	
	@Test
	public void sn21_이름_수정() {
		
		try {
			accountService.modify("홍길동2");
		} catch (TimeoutException e) {
		}
		
		Optional<Account> account = accountRepository.findByAccountId(1L);
		assertThat(account.get().getUsername()).isEqualTo("홍길동2");
	}
	
	@Test
	public void sn30_테스트데이터_삭제() {
		accountRepository.deleteAll();
	}
	
}
