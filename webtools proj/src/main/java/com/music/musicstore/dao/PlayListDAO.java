package com.music.musicstore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.music.musicstore.pojo.PlayList;
import com.music.musicstore.pojo.Subscriber;
import com.music.musicstore.pojo.Track;

public class PlayListDAO extends DAO {

	public PlayList addPlaylist(PlayList playlist, Subscriber subscriber) {

		try {
			begin();
			getSession().save(playlist);
			getSession().save(subscriber);
			commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		close();
		return playlist;
	}

	@SuppressWarnings("deprecation")
	public List<PlayList> getPlaylists(String email) {

		try {
			begin();
			Criteria criteria = getSession().createCriteria(PlayList.class);
			Criteria criteriaSubscriber = getSession().createCriteria("subscriber");

			criteriaSubscriber.add(Restrictions.eq("email", email));

			List<PlayList> list = criteria.list();
			close();
			if (list.size() == 0) {
				return new ArrayList<PlayList>();
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
			close();
		}

		return null;
	}

	public int deletePlaylist(int id) {
		int count = 0;
		try {
			begin();
			Query q = getSession().createQuery("delete from PlayList where playlist_Id=:id").setParameter("id", id);
			count = q.executeUpdate();
			commit();
		} catch (Exception e) {
		} finally {
			close();
		}
		return count;
	}

	public Set<Track> playlisttracks(String playlistid) {
		Set<Track> tracks = null;
		try {
			Query q = getSession().createQuery("from PlayList where playlist_Id=:playlistid").setParameter("playlistid",
					Integer.parseInt(playlistid));
			tracks = ((PlayList) q.uniqueResult()).getTracks();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
		return tracks;
	}

	public PlayList getplaylist(int playlistid) {
		PlayList playList = null;
		try {
			Query q = getSession().createQuery("from PlayList where playlist_Id=:playlistid").setParameter("playlistid",
					playlistid);
			playList = (PlayList) q.uniqueResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
		return playList;
	}

	public void updatePlayList(PlayList playlist) {
		try {
			begin();
			getSession().saveOrUpdate(playlist);
			commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}

	}

}
