package com.music.musicstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import com.music.musicstore.pojo.Subscriber;

public class SubscriberDAO extends DAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public ArrayList<Subscriber> getAllSubscriber() {
		ArrayList<Subscriber> subscriberList = new ArrayList<Subscriber>();
		try {
			begin();
			Query<Subscriber> q = getSession().createQuery("from Subscriber");
			subscriberList = (ArrayList<Subscriber>) q.list();
			close();
		} catch (Exception e) {
			System.out.println(e);
			close();
		}
		return subscriberList;
	}

	public int deleteSubscribers(int id) {
		int count = 0;
		try {
			begin();
			Query q = getSession().createQuery("delete from PlayList where id=:id").setParameter("id", id);
			count = q.executeUpdate();
			commit();
		} catch (Exception e) {
		} finally {
			close();
		}
		return count;
	}

	public Subscriber getSubscriber(String email) {
		// TODO Auto-generated method stub
		Subscriber subscriber = null;
		try {
			begin();
			Query query = getSession().createQuery("from Subscriber where email=:email");
			query.setParameter("email", email);
			subscriber = (Subscriber) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return subscriber;
	}

	public List getNumberOfSubscribers() {
		List result = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Subscriber.class);
			crit.setProjection(Projections.rowCount());
			result = crit.list();
			System.out.println("Result : " + result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return result;
	}
}