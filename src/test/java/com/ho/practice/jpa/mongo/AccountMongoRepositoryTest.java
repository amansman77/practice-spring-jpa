package com.ho.practice.jpa.mongo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ho.practice.jpa.util.DateUtil;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountMongoRepositoryTest {

	@Autowired
	AccountMongoRepository accountMongoRepository;

	@After
	public void initDB() {
		accountMongoRepository.deleteByAccountId("id1");
		accountMongoRepository.deleteByAccountId("id2");
	}
	
	@Test
	public void findAllTest() {
		//given
		List<AccountMongo> list = 
				  Arrays.asList(
						  AccountMongo.builder().accountId("id1").value("text1")
							.registDate(LocalDateTime.parse("2019-10-21 09:57:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
							.build()
						  , AccountMongo.builder().accountId("id1").value("text2")
							.registDate(LocalDateTime.parse("2019-10-21 09:57:06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
							.build()
						  , AccountMongo.builder().accountId("id1").value("text3")
						  	.registDate(LocalDateTime.parse("2019-10-21 09:57:07:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
						  	.build()
						  , AccountMongo.builder().accountId("id1").value("text4")
						  	.registDate(LocalDateTime.parse("2019-10-21 09:57:08:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
						  	.build()
						  , AccountMongo.builder().accountId("id2").value("text5")
						  	.registDate(LocalDateTime.parse("2019-10-21 09:57:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
						  	.build()
				                );
		
		//when
		accountMongoRepository.saveAll(list);
		List<AccountMongo> resultList = accountMongoRepository.findAll();
		
		//then
		assertThat(resultList).isNotNull();
		assertThat(resultList.size()).isEqualTo(list.size());
		assertThat(resultList.get(0).getAccountId()).isEqualTo(list.get(0).getAccountId());
		assertThat(resultList.get(0).getValue()).isEqualTo(list.get(0).getValue());
		
	}
	
	@Test
	public void findByRegistDateBetweenTest() {
		//given
		String accountId = "id1";
		String startDate = "20191021095705";
		String endDate = "20191021095708";
		
		List<AccountMongo> list = 
				  Arrays.asList(
						  AccountMongo.builder().accountId("id1").value("text1")
							.registDate(DateUtil.getLocalDateTime("2019-10-21 09:57:05:00", "yyyy-MM-dd HH:mm:ss:SS"))
							.build()
						  , AccountMongo.builder().accountId("id1").value("text2")
							.registDate(LocalDateTime.parse("2019-10-21 09:57:06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
							.build()
						  , AccountMongo.builder().accountId("id1").value("text3")
						  	.registDate(LocalDateTime.parse("2019-10-21 09:57:07:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
						  	.build()
						  , AccountMongo.builder().accountId("id1").value("text4")
						  	.registDate(LocalDateTime.parse("2019-10-21 09:57:08:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
						  	.build()
						  , AccountMongo.builder().accountId("id2").value("text5")
						  	.registDate(LocalDateTime.parse("2019-10-21 09:57:05:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")))
						  	.build()
				                );
		
		//when
		accountMongoRepository.saveAll(list);
		List<AccountMongo> resultList = accountMongoRepository.findByRegistDateBetween(
				DateUtil.getLocalDateTime(startDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS)
				, DateUtil.getLocalDateTime(endDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS));
		List<AccountMongo> resultList2 = accountMongoRepository.findByRegistDateBetweenQuery(
				DateUtil.getLocalDateTime(startDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS)
				, DateUtil.getLocalDateTime(endDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS));
		List<AccountMongo> resultList3 = accountMongoRepository.findByAccountIdAndTimekeyBetweenQuery(
				accountId
				, DateUtil.getLocalDateTime(startDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS)
				, DateUtil.getLocalDateTime(endDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS));
		Stream<AccountMongo> stream = accountMongoRepository.findByAccountIdAndTimekeyBetweenQuery(
				accountId
				, DateUtil.getLocalDateTime(startDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS)
				, DateUtil.getLocalDateTime(endDate, DateUtil.PATTERN_DATE_TIME_YYYYMMDDHHMMSS))
				.stream();
		List<AccountMongo> streamResult = stream.collect(Collectors.toList());
		
		//then
		assertThat(resultList).isNotNull();
		assertThat(resultList.size()).isEqualTo(2);
		assertThat(resultList.get(0).getAccountId()).isEqualTo(list.get(1).getAccountId());
		assertThat(resultList.get(0).getValue()).isEqualTo(list.get(1).getValue());
		assertThat(resultList.get(0).getRegistDate()).isEqualTo(list.get(1).getRegistDate());
		
		assertThat(resultList2).isNotNull();
		assertThat(resultList2.size()).isEqualTo(5);
		assertThat(resultList2.get(0).getAccountId()).isEqualTo(list.get(0).getAccountId());
		assertThat(resultList2.get(0).getValue()).isEqualTo(list.get(0).getValue());
		assertThat(resultList2.get(0).getRegistDate()).isEqualTo(list.get(0).getRegistDate());
		
		assertThat(resultList3).isNotNull();
		assertThat(resultList3.size()).isEqualTo(4);
		assertThat(resultList3.get(0).getAccountId()).isEqualTo(list.get(0).getAccountId());
		assertThat(resultList3.get(0).getValue()).isEqualTo(list.get(0).getValue());
		assertThat(resultList3.get(0).getRegistDate()).isEqualTo(list.get(0).getRegistDate());
		
		assertThat(streamResult).isNotNull();
		assertThat(streamResult.size()).isEqualTo(4);
		assertThat(streamResult.get(0).getAccountId()).isEqualTo(list.get(0).getAccountId());
		assertThat(streamResult.get(0).getValue()).isEqualTo(list.get(0).getValue());
		assertThat(streamResult.get(0).getRegistDate()).isEqualTo(list.get(0).getRegistDate());
		
	}
	
}
