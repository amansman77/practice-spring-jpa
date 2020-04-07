package com.ho.practice.jpa.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Member member = new Member();
			member.setId(1L);
			member.setName("HelloA");
			em.persist(member);
			
			tx.commit();
			
			tx.begin();
			
			Member findMember = em.find(Member.class, 1L);
			findMember.setName("HelloJPA");
			System.out.println("member.name = " + findMember.getName());
			
			tx.commit();
			
			tx.begin();
			
			List<Member> results = em.createQuery("select m from Member as m", Member.class)
				.setFirstResult(5)
				.setMaxResults(8)
				.getResultList();
			for (Member result : results) {
				System.out.println("member.name = " + result.getName());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		emf.close();
	}

}
