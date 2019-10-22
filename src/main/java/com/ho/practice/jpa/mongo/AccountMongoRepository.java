package com.ho.practice.jpa.mongo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AccountMongoRepository extends MongoRepository<AccountMongo, String> {

	void deleteByAccountId(String accountId);

	/**
	 * Between은 startDate와 endDate를 포함하지 않음
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<AccountMongo> findByRegistDateBetween(LocalDateTime startDate, LocalDateTime endDate);

	@Query("{" + 
			"    'registDate': {" + 
			"        '$gte': ?0," + 
			"        '$lte': ?1" + 
			"    }" + 
			"}")
	List<AccountMongo> findByRegistDateBetweenQuery(LocalDateTime startDate, LocalDateTime endDate);

	@Query("{" + 
			"    $and: [{" + 
			"        accountId: ?0" + 
			"    }, {" + 
			"        registDate: {" + 
			"            $gte: ?1," + 
			"            $lte: ?2" + 
			"        }" + 
			"    }]" + 
			"}")
	List<AccountMongo> findByAccountIdAndTimekeyBetweenQuery(String accountId, LocalDateTime startDate,
			LocalDateTime endDate);
	
}
