package com.ho.practice.jpa.mongo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

//@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@DataMongoTest
//@Import(MongoDBConfiguration.class)
public class SensorRawdataRepositoryTest {

	@Autowired
	AccountMongoRepository accountMongoRepository;

	@Test
	public void findAllTest() {
		//given
		
		List<AccountMongo> list = 
				  Arrays.asList(
						  AccountMongo.builder().id("id1").value("text1").build()
						  , AccountMongo.builder().id("id2").value("text2").build()
						  , AccountMongo.builder().id("id3").value("text3").build()
						  , AccountMongo.builder().id("id4").value("text4").build()
						  , AccountMongo.builder().id("id5").value("text5").build()
				                );
		
		//when
		accountMongoRepository.saveAll(list);
		List<AccountMongo> resultList = accountMongoRepository.findAll();
		
		//then
		assertThat(resultList).isNotNull();
		assertThat(resultList.size()).isEqualTo(list.size());
		assertThat(resultList.get(0).getId()).isEqualTo(list.get(0).getId());
		assertThat(resultList.get(0).getValue()).isEqualTo(list.get(0).getValue());
		
	}
	
}
