package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.prs.business.Product;

public class ProductDB {

	public static Product getProductByID(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Product product = null;
		try {
			TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class);
			q.setParameter("id", id);
			product = q.getSingleResult();
		} finally {
			em.close();
		}

		return product;
	}

	public static List<Product> getAll() {
		List<Product> products = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			TypedQuery<Product> q = em.createQuery("SELECT p from Product p", Product.class);
			products = q.getResultList();
		} finally {
			em.close();
		}

		return products;
	}

	public static void insert(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(product);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(product);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Product product) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(product));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
