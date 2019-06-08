package com.music.musicstore.dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.music.musicstore.pojo.Artist;
import com.music.musicstore.pojo.Subscriber;
import com.music.musicstore.pojo.User;
import com.music.musicstore.pojo.User.UserRole;

@SuppressWarnings("deprecation")
public class UserDAO extends DAO {

	public User getUser(String email) {
		User user1 = null;
		try {
			begin();
			Query q = getSession().createQuery("from User where email = '" + email + "'");
			user1 = (User) q.uniqueResult();
			if (user1 == null) {
				Criteria crit = getSession().createCriteria(Artist.class);
				crit.add(Restrictions.eq("email", email));
				crit.setMaxResults(1);
				user1 = (User) crit.uniqueResult();
			}
			if (user1 == null) {
				Criteria crit = getSession().createCriteria(Subscriber.class);
				crit.add(Restrictions.eq("email", email));
				crit.setMaxResults(1);
				user1 = (User) crit.uniqueResult();
			}
		} catch (Exception e) {
			rollback();
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return user1;
	}

	// to save user when registered
	public User create(User user) {
		try {
			begin();
			if (user.getRole().equals(UserRole.ARTIST.getRole())) {
				Artist artist = new Artist(user);
				getSession().save(artist);
			} else {
				if (user.getRole().equals(UserRole.SUBSCRIBER.getRole())) {
					Subscriber subscriber = new Subscriber(user);
					getSession().save(subscriber);
				}
			}
			commit();
		} catch (Exception e) {
			rollback();
		} finally {
			close();
		}

		return user;

	}

	public User login(String email, String password) {
		User user = null;
		try {
			user = this.getUser(email);
			if (!user.getPassword().equals(password)) {
				user = null;
			}
		} catch (Exception e) {
			rollback();
			System.out.println(e);
		} finally {
			close();
		}
		return user;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public boolean updateUser(String email) throws Exception {
		try {
			begin();
			System.out.println(email);
			Query q = getSession().createQuery("from User where email = :email");
			q.setString("email", email);
			User user = (User) q.uniqueResult();
			System.out.println("ID: " + user.getId());
			if (user != null) {
				System.out.println("ABC-------------");
				user.setStatus(1);
				getSession().update(user);
				commit();
				close();
				return true;
			}

		} catch (HibernateException e) {
			rollback();
			close();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
		return false;

	}

}
