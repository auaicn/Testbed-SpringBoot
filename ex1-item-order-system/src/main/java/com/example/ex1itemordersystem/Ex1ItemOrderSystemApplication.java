package com.example.ex1itemordersystem;

import domain.Member;
import domain.Order;
import domain.OrderStatus;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// @SpringBootApplication
public class Ex1ItemOrderSystemApplication {

	public static void main(String[] args) {
		// SpringApplication.run(Ex1ItemOrderSystemApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("order");
		EntityManager em =  emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
//			Member member = new Member();
//			member.setCity("Seoul");
//			member.setName("kyung ho");
//			member.setZIPCODE("06508");
//			member.setId(88L);
//
//			Order order = new Order();
//			order.setOrderedDate(new Date());
//			order.setStatus(OrderStatus.CONFIRMED);
//
//			member.setOrder(order);
			tx.commit();
		}catch (Exception e){
			tx.rollback();
		}finally {
			em.close();
		}

		emf.close();

	}

}
