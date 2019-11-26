package com.ho.practice.jpa.compositeid;

import java.io.Serializable;

/**
 * 수강하는 수업 ID
 * @author hhsung
 *
 */
public class RegisterCourseId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 926910087227599305L;
	
	private Long stugentId;
    private Long courseId;

}
