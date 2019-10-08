package com.ho.practice.jpa.school;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * 수강하는 수업 entity
 * @author MICUBE
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
