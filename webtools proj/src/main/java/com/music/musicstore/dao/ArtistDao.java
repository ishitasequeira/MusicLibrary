package com.music.musicstore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import com.music.musicstore.pojo.Artist;

public class ArtistDao extends DAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public ArrayList<Artist> getAllArtist() {
		ArrayList<Artist> artistList = new ArrayList<Artist>();
		try {
			begin();
			Query q = getSession().createQuery("from Artist");
			artistList = (ArrayList<Artist>) q.list();
			System.out.println(artistList.size() + "fgbhnm,zxmncfb");
			close();
		} catch (Exception e) {
			System.out.println(e);
			close();
		}

		return artistList;
	}

	public int deleteartist(int id) {
		int count = 0;
		try {
			begin();
			Query q = getSession().createQuery("delete from Artist where id=:id").setParameter("id", id);
			count = q.executeUpdate();
		} catch (Exception e) {
		} finally {
			close();
		}
		return count;
	}

	public List getNumberOfArtists() {
		List result = null;
		try {
			begin();
			Criteria crit = getSession().createCriteria(Artist.class);
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
