package com.example.ex1itemordersystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
public class Ex1ItemOrderSystemApplication {

	public static void main(String[] args) {
		// SpringApplication.run(Ex1ItemOrderSystemApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		EntityManager em =  emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		// EntityManagerFactory emf = new EntityManagerFactory();
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
//		EntityManager em = emf.createEntityManager();
//

	}

}
