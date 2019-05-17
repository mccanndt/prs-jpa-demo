package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.prs.business.Vendor;

public class VendorDB {

	public static Vendor getVendorByCode(String code) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Vendor vendor = null;
		try {
			TypedQuery<Vendor> q = em.createQuery("SELECT v from Vendor v WHERE v.code = :code", Vendor.class);
			q.setParameter("code", code);
			vendor = q.getSingleResult();
		} finally {
			em.close();
		}

		return vendor;
	}

	public static List<Vendor> getAll() {
		List<Vendor> vendors = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			TypedQuery<Vendor> q = em.createQuery("SELECT v from Vendor v", Vendor.class);
			vendors = q.getResultList();
		} finally {
			em.close();
		}

		return vendors;
	}

	public static void insert(Vendor vendor) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(vendor);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Vendor vendor) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(vendor);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Vendor vendor) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(vendor));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
}
