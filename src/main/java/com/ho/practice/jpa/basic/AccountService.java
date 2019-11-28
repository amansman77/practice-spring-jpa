package com.ho.practice.jpa.basic;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void modify(String username) throws TimeoutException {
		Account findAccount = accountRepository.findByAccountId(1L).get();
		
		findAccount.setUsername(username);
		accountRepository.flush();
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void modify_flush_occursCheckedException(String username) throws TimeoutException {
		Account findAccount = accountRepository.findByAccountId(1L).get();
		
		findAccount.setUsername(username);
		accountRepository.flush();
		
		throw new TimeoutException();
	}
	
}
