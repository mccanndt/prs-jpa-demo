package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.prs.business.PurchaseRequestLineItem;

public class PurchaseRequestLineItemDB {

	public static PurchaseRequestLineItem getPurchaseRequestByID(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		PurchaseRequestLineItem pr = null;
		try {
			TypedQuery<PurchaseRequestLineItem> q = em.createQuery("SELECT p from PurchaseRequestLineItem p WHERE p.id = :id", PurchaseRequestLineItem.class);
			q.setParameter("id", id);
			pr = q.getSingleResult();
		} finally {
			em.close();
		}

		return pr;
	}

	public static List<PurchaseRequestLineItem> getAll() {
		List<PurchaseRequestLineItem> purchaseRequestLineItems = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			TypedQuery<PurchaseRequestLineItem> q = em.createQuery("SELECT p from PurchaseRequestLineItem p", PurchaseRequestLineItem.class);
			purchaseRequestLineItems = q.getResultList();
		} finally {
			em.close();
		}

		return purchaseRequestLineItems;
	}

	public static void insert(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(purchaseRequestLineItem);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(purchaseRequestLineItem);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(PurchaseRequestLineItem purchaseRequestLineItem) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(purchaseRequestLineItem));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
