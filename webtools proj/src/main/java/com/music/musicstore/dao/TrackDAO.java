package com.music.musicstore.dao;

import java.util.List;

import org.hibernate.Query;

import com.music.musicstore.pojo.Album;
import com.music.musicstore.pojo.Track;

public class TrackDAO extends DAO {

	public Track addTrack(Track track, Album album) {

		try {
			begin();
			getSession().saveOrUpdate(album);
			getSession().save(track);
			commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		close();
		return track;
	}

	public List<Track> getTracks(String param) {
		List<Track> tracks = null;
		try {
			begin();
			Query q = getSession().createQuery("from Track where title like '%" + param + "%'");
			// q.setParameter("title", "%"+param+"%");
			System.out.println(q.toString());
			tracks = q.list();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return tracks;
	}

	public Track getTrack(int parameter) {
		// TODO Auto-generated method stub
		Track track = null;
		try {
			begin();
			Query q = getSession().createQuery("from Track where track_Id=:trackid");
			q.setParameter("trackid", parameter);
			track = (Track) q.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return track;
	}

}
