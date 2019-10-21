package com.ho.practice.jpa.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@Document
public class AccountMongo {

	@NonNull
	private String id;
	
	@NonNull
	private String value;
	
}
