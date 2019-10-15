package com.ho.practice.jpa.compositeid;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ho.practice.jpa.compositeid.Course;
import com.ho.practice.jpa.compositeid.CourseRepository;
import com.ho.practice.jpa.compositeid.Student;
import com.ho.practice.jpa.compositeid.StudentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableJpaRepositories("com.ho.practice.jpa.compositeid")
public class SchoolRepositoryTest {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void accountTest() throws SQLException {
		//given
		Course course = new Course();
		Student student = new Student();
		
		//when
		Course newCourse = courseRepository.save(course);
		Student newStudent = studentRepository.save(student);
		
		//then
		assertThat(newCourse).isNotNull();
		assertThat(newStudent).isNotNull();
		assertThat(newCourse.getCourseId()).isEqualTo(1L);
		assertThat(newStudent.getStugentId()).isEqualTo(1L);
	}
	
}
