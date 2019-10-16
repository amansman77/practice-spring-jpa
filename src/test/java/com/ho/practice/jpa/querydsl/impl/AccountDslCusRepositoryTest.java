package com.ho.practice.jpa.querydsl.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDslCusRepositoryTest {
	
	@Autowired
	AccountDslCusRepository accountDslCusRepository;
	
	@Test
	public void findByGenderTest() throws SQLException {
		//given
		List<AccountDslCus> list = 
				  Arrays.asList(new AccountDslCus(1L, "user1", "userpass1", "m")
						  , new AccountDslCus(2L, "user2", "userpass2", "m")
						  , new AccountDslCus(3L, "user3", "userpass3", "m")
						  , new AccountDslCus(4L, "user4", "userpass4", "m")
						  , new AccountDslCus(5L, "user5", "userpass5", "m")
						  , new AccountDslCus(6L, "user6", "userpass6", "w")
						  , new AccountDslCus(7L, "user7", "userpass7", "w")
						  , new AccountDslCus(8L, "user8", "userpass8", "w")
						  , new AccountDslCus(9L, "user9", "userpass9", "w")
						  , new AccountDslCus(10L, "user10", "userpass10", "w")
				                );
		
		accountDslCusRepository.saveAll(list);
		
		//when
		List<AccountDslCus> manList = accountDslCusRepository.findByGender("m");
		
		//then
		assertThat(manList).isNotNull();
		assertThat(manList.size()).isEqualTo(5);
		
	}
	
	@Test
	public void findDynamicQueryTest() throws SQLException {
		//given
		List<AccountDslCus> list = 
				  Arrays.asList(new AccountDslCus(1L, "user1", "userpass1", "m")
						  , new AccountDslCus(2L, "user2", "userpass2", "m")
						  , new AccountDslCus(3L, "user3", "userpass3", "m")
						  , new AccountDslCus(4L, "user4", "userpass4", "m")
						  , new AccountDslCus(5L, "user5", "userpass5", "m")
						  , new AccountDslCus(6L, "user6", "userpass6", "w")
						  , new AccountDslCus(7L, "user7", "userpass7", "w")
						  , new AccountDslCus(8L, "user8", "userpass8", "w")
						  , new AccountDslCus(9L, "user9", "userpass9", "w")
						  , new AccountDslCus(10L, "user10", "userpass10", "w")
				                );
		
		accountDslCusRepository.saveAll(list);
		
		//when
		List<AccountDslCus> list1 = accountDslCusRepository.findDynamicQuery("user1", "m");
		List<AccountDslCus> list2 = accountDslCusRepository.findDynamicQuery("user1", null);
		List<AccountDslCus> list3 = accountDslCusRepository.findDynamicQuery(null, "m");
		List<AccountDslCus> list4 = accountDslCusRepository.findDynamicQuery(null, null);
		
		//then
		assertThat(list1).isNotNull();
		assertThat(list1.size()).isEqualTo(1);
		assertThat(list1.get(0).getUsername()).isEqualTo("user1");
		assertThat(list1.get(0).getGender()).isEqualTo("m");
		
		assertThat(list2).isNotNull();
		assertThat(list2.size()).isEqualTo(1);
		assertThat(list2.get(0).getUsername()).isEqualTo("user1");
		assertThat(list2.get(0).getGender()).isEqualTo("m");
		
		assertThat(list3).isNotNull();
		assertThat(list3.size()).isEqualTo(5);
		assertThat(list3.get(0).getUsername()).isEqualTo("user1");
		assertThat(list3.get(0).getGender()).isEqualTo("m");
		
		assertThat(list4).isNotNull();
		assertThat(list4.size()).isEqualTo(10);
		assertThat(list4.get(0).getUsername()).isEqualTo("user1");
		assertThat(list4.get(0).getGender()).isEqualTo("m");
		
	}
	
	@Test
	public void findDynamicQueryPageTest() throws SQLException {
		//given
		List<AccountDslCus> list = 
				  Arrays.asList(new AccountDslCus(1L, "user1", "userpass1", "m")
						  , new AccountDslCus(2L, "user2", "userpass2", "m")
						  , new AccountDslCus(3L, "user3", "userpass3", "m")
						  , new AccountDslCus(4L, "user4", "userpass4", "m")
						  , new AccountDslCus(5L, "user5", "userpass5", "m")
						  , new AccountDslCus(6L, "user6", "userpass6", "w")
						  , new AccountDslCus(7L, "user7", "userpass7", "w")
						  , new AccountDslCus(8L, "user8", "userpass8", "w")
						  , new AccountDslCus(9L, "user9", "userpass9", "w")
						  , new AccountDslCus(10L, "user10", "userpass10", "w")
				                );
		Pageable pageable1 = PageRequest.of(1, 2);
		Pageable pageable2 = PageRequest.of(2, 2);
		
		accountDslCusRepository.saveAll(list);
		
		//when
		List<AccountDslCus> list1 = accountDslCusRepository.findDynamicQueryPage(null, "w", pageable1);
		List<AccountDslCus> list2 = accountDslCusRepository.findDynamicQueryPage(null, "w", pageable2);
		List<AccountDslCus> list4 = accountDslCusRepository.findDynamicQueryPage(null, null, pageable1);
		
		//then
		assertThat(list1).isNotNull();
		assertThat(list1.size()).isEqualTo(2);
		assertThat(list1.get(0).getUsername()).isEqualTo("user8");
		assertThat(list1.get(0).getGender()).isEqualTo("w");
		
		assertThat(list2).isNotNull();
		assertThat(list2.size()).isEqualTo(1);
		assertThat(list2.get(0).getUsername()).isEqualTo("user10");
		assertThat(list2.get(0).getGender()).isEqualTo("w");
		
		assertThat(list4).isNotNull();
		assertThat(list4.size()).isEqualTo(2);
		assertThat(list4.get(0).getUsername()).isEqualTo("user3");
		assertThat(list4.get(0).getGender()).isEqualTo("m");
		
	}
	
	@Test
	public void findDynamicQueryAdvanceTest() throws SQLException {
		//given
		List<AccountDslCus> list = 
				  Arrays.asList(new AccountDslCus(1L, "user1", "userpass1", "m")
						  , new AccountDslCus(2L, "user2", "userpass2", "m")
						  , new AccountDslCus(3L, "user3", "userpass3", "m")
						  , new AccountDslCus(4L, "user4", "userpass4", "m")
						  , new AccountDslCus(5L, "user5", "userpass5", "m")
						  , new AccountDslCus(6L, "user6", "userpass6", "w")
						  , new AccountDslCus(7L, "user7", "userpass7", "w")
						  , new AccountDslCus(8L, "user8", "userpass8", "w")
						  , new AccountDslCus(9L, "user9", "userpass9", "w")
						  , new AccountDslCus(10L, "user10", "userpass10", "w")
				                );
		
		accountDslCusRepository.saveAll(list);
		
		//when
		List<AccountDslCus> list1 = accountDslCusRepository.findDynamicQueryAdvance("user1", "m");
		List<AccountDslCus> list2 = accountDslCusRepository.findDynamicQueryAdvance("user1", null);
		List<AccountDslCus> list3 = accountDslCusRepository.findDynamicQueryAdvance(null, "m");
		List<AccountDslCus> list4 = accountDslCusRepository.findDynamicQueryAdvance(null, null);
		
		//then
		assertThat(list1).isNotNull();
		assertThat(list1.size()).isEqualTo(1);
		assertThat(list1.get(0).getUsername()).isEqualTo("user1");
		assertThat(list1.get(0).getGender()).isEqualTo("m");
		
		assertThat(list2).isNotNull();
		assertThat(list2.size()).isEqualTo(1);
		assertThat(list2.get(0).getUsername()).isEqualTo("user1");
		assertThat(list2.get(0).getGender()).isEqualTo("m");
		
		assertThat(list3).isNotNull();
		assertThat(list3.size()).isEqualTo(5);
		assertThat(list3.get(0).getUsername()).isEqualTo("user1");
		assertThat(list3.get(0).getGender()).isEqualTo("m");
		
		assertThat(list4).isNotNull();
		assertThat(list4.size()).isEqualTo(10);
		assertThat(list4.get(0).getUsername()).isEqualTo("user1");
		assertThat(list4.get(0).getGender()).isEqualTo("m");
		
	}
	
	@Test
	public void findDynamicQueryAdvancePageTest() throws SQLException {
		//given
		List<AccountDslCus> list = 
				  Arrays.asList(new AccountDslCus(1L, "user1", "userpass1", "m")
						  , new AccountDslCus(2L, "user2", "userpass2", "m")
						  , new AccountDslCus(3L, "user3", "userpass3", "m")
						  , new AccountDslCus(4L, "user4", "userpass4", "m")
						  , new AccountDslCus(5L, "user5", "userpass5", "m")
						  , new AccountDslCus(6L, "user6", "userpass6", "w")
						  , new AccountDslCus(7L, "user7", "userpass7", "w")
						  , new AccountDslCus(8L, "user8", "userpass8", "w")
						  , new AccountDslCus(9L, "user9", "userpass9", "w")
						  , new AccountDslCus(10L, "user10", "userpass10", "w")
				                );
		Pageable pageable1 = PageRequest.of(1, 2);
		Pageable pageable2 = PageRequest.of(2, 2);
		
		accountDslCusRepository.saveAll(list);
		
		//when
		List<AccountDslCus> list1 = accountDslCusRepository.findDynamicQueryAdvance(null, "w", pageable1);
		List<AccountDslCus> list2 = accountDslCusRepository.findDynamicQueryAdvance(null, "w", pageable2);
		List<AccountDslCus> list4 = accountDslCusRepository.findDynamicQueryAdvance(null, null, pageable1);
		
		//then
		assertThat(list1).isNotNull();
		assertThat(list1.size()).isEqualTo(2);
		assertThat(list1.get(0).getUsername()).isEqualTo("user8");
		assertThat(list1.get(0).getGender()).isEqualTo("w");
		
		assertThat(list2).isNotNull();
		assertThat(list2.size()).isEqualTo(1);
		assertThat(list2.get(0).getUsername()).isEqualTo("user10");
		assertThat(list2.get(0).getGender()).isEqualTo("w");
		
		assertThat(list4).isNotNull();
		assertThat(list4.size()).isEqualTo(2);
		assertThat(list4.get(0).getUsername()).isEqualTo("user3");
		assertThat(list4.get(0).getGender()).isEqualTo("m");
		
	}
	
}
