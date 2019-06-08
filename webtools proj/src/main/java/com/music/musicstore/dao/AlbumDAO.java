package com.music.musicstore.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.music.musicstore.pojo.Album;
import com.music.musicstore.pojo.Artist;
import com.music.musicstore.pojo.Track;

public class AlbumDAO extends DAO {

	public Album addAlbum(Album album, Artist artist) {

		try {
			begin();
			getSession().save(album);
			getSession().save(artist);
			commit();
		} catch (Exception e) {
			System.out.println(e);
		}
		close();
		return album;
	}

	@SuppressWarnings("deprecation")
	public List<Album> getAlbums(String email) {

		try {
			begin();

			Criteria criteriaArtist = getSession().createCriteria("artist");
			Criteria criteriaAlbum = getSession().createCriteria(Album.class);
			criteriaArtist.add(Restrictions.eq("email", email));
			criteriaAlbum.add(Restrictions.eq("status", "ACCEPTED"));

			List<Album> list = criteriaAlbum.list();
			close();
			if (list.size() == 0) {
				return new ArrayList<Album>();
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
			close();
		}

		return null;
	}

	public Album getAlbum(String albumId) {
		begin();
		Query q = getSession().createQuery("from Album where album_id = :albumId ");
		q.setParameter("albumId", Integer.parseInt(albumId));
		Album album = (Album) q.uniqueResult();
		close();
		return album;
	}

	public Set<Album> getAlbumList(String email) {
		try {
			begin();
			Query query = getSession().createQuery(" from Artist where email =:email").setParameter("email", email);
			query.setMaxResults(1);
			List<Artist> list = query.list();
			close();
			if (list.size() == 0) {
				return new HashSet<Album>();
			}
			return list.get(0).getAlbums();
		} catch (Exception e) {
			System.out.println(e);
			close();
		}

		return null;
	}

	public Set<Album> getPendingAlbumList(String email) {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Artist.class);
			Criteria albumcriteria = crit.createCriteria("albums");
			crit.add(Restrictions.eq("email", email));
			albumcriteria.add(Restrictions.eq("status", "PENDING"));
			crit.setMaxResults(1);
			List<Artist> list = crit.list();
			close();
			if (list.size() == 0) {
				return new HashSet<Album>();
			}
			return list.get(0).getAlbums();
		} catch (Exception e) {
			System.out.println(e);
			close();
		}

		return null;
	}

	public List<Album> getviewalbumrequests() {
		try {
			begin();
			Query q = getSession().createQuery("from Album where status=:status").setParameter("status", "PENDING");
			List<Album> album = q.list();
			close();
			if (album.size() == 0) {
				return new ArrayList();
			}
			return album;
		} catch (Exception e) {
			System.out.println(e);
			close();
		}
		return null;

	}

	public int updateAlbumStatus(String status, String albumid) {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			begin();
			Query q = getSession().createQuery("update Album set status=:status where album_id=:albumid")
					.setParameter("status", status);
			q.setParameter("albumid", Integer.parseInt(albumid));
			count = q.executeUpdate();
			System.out.println("count" + count);
			commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close();
		}
		return count;
	}

	public List<Track> getviewalbumtracks(int album_id) {
		try {
			begin();
			Query q = getSession().createQuery("from Track");
			List<Track> tracks = q.list();

			List<Track> albumTracks = new ArrayList();

			for (Track t : tracks) {
				if (t.getAlbum().getAlbum_id() == album_id) {
					albumTracks.add(t);
				}
			}
			close();
			return albumTracks;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
		// return tracks;
	}

}
