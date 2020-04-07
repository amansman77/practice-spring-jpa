package com.ho.practice.jpa.mongo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
//@Document
public class AccountMongo {

	@NonNull
	private String accountId;
	
	@NonNull
	private String value;
	
	@NonNull
	private LocalDateTime registDate;
	
}
