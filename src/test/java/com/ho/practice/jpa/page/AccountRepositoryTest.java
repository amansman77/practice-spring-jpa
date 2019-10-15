package com.ho.practice.jpa.page;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	AccountPageRepository accountPageRepository;

	@Test
	public void pageTest() throws SQLException {
		//given
		List<AccountPage> list = 
				  Arrays.asList(new AccountPage(1L, "user1", "userpass1", "m")
						  , new AccountPage(2L, "user2", "userpass2", "m")
						  , new AccountPage(3L, "user3", "userpass3", "m")
						  , new AccountPage(4L, "user4", "userpass4", "m")
						  , new AccountPage(5L, "user5", "userpass5", "m")
						  , new AccountPage(6L, "user6", "userpass6", "w")
						  , new AccountPage(7L, "user7", "userpass7", "w")
						  , new AccountPage(8L, "user8", "userpass8", "w")
						  , new AccountPage(9L, "user9", "userpass9", "w")
						  , new AccountPage(10L, "user10", "userpass10", "w")
				                );
		
		accountPageRepository.saveAll(list);
		
		//when
		List<AccountPage> allList = accountPageRepository.findAll();
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<AccountPage> page = accountPageRepository.findAll(pageable);
		
		//then
		assertThat(allList).isNotNull();
		assertThat(allList.size()).isEqualTo(list.size());
		
		assertThat(page).isNotNull();
		assertThat(page.getTotalElements()).isEqualTo(list.size());
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.getNumberOfElements()).isEqualTo(5);
	
	}
	
}
