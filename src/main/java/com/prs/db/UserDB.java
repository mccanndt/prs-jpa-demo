package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.prs.business.User;

public class UserDB {

	public static User getUserByEmail(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		User user = null;
		try {
			TypedQuery<User> q = em.createQuery("SELECT u from User u WHERE u.email = :email", User.class);
			q.setParameter("email", email);
			user = q.getSingleResult();
		} finally {
			em.close();
		}

		return user;
	}

	public static List<User> getAll() {
		List<User> users = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			TypedQuery<User> q = em.createQuery("SELECT u from User u", User.class);
			users = q.getResultList();
		} finally {
			em.close();
		}

		return users;
	}

	public static void insert(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
