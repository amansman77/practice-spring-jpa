package com.ho.practice.jpa.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static final String PATTERN_DATE_TIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	public static String getNow(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static String toString(LocalDateTime time, String pattern) {
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}
	
	public static LocalDateTime getLocalDateTime(String time, String pattern) {
		return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
	}
	
}
