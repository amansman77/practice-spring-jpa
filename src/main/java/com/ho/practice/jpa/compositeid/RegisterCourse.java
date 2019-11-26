package com.ho.practice.jpa.compositeid;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 수강하는 수업 entity
 * @author hhsung
 *
 */
@Entity
@IdClass(RegisterCourseId.class)
public class RegisterCourse {

    @Id
    private Long stugentId;
    @Id
    private Long courseId;
    
}
