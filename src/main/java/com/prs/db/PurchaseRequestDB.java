package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.prs.business.PurchaseRequest;

public class PurchaseRequestDB {

	public static PurchaseRequest getPurchaseRequestByID(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		PurchaseRequest pr = null;
		try {
			TypedQuery<PurchaseRequest> q = em.createQuery("SELECT p from PurchaseRequest p WHERE p.id = :id", PurchaseRequest.class);
			q.setParameter("id", id);
			pr = q.getSingleResult();
		} finally {
			em.close();
		}

		return pr;
	}

	public static List<PurchaseRequest> getAll() {
		List<PurchaseRequest> purchaseRequests = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			TypedQuery<PurchaseRequest> q = em.createQuery("SELECT p from PurchaseRequest p", PurchaseRequest.class);
			purchaseRequests = q.getResultList();
		} finally {
			em.close();
		}

		return purchaseRequests;
	}

	public static void insert(PurchaseRequest purchaseRequest) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(purchaseRequest);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(PurchaseRequest purchaseRequest) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(purchaseRequest);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(PurchaseRequest purchaseRequest) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(purchaseRequest));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
