package com.music.musicstore.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Album")

public class Album {
	public enum Status{
		ACCEPTED("ACCEPTED") ,REJECTED("REJECTED"), PENDING("PENDING");
		private String role;

		public String getRole() {
			return role;
		}
		Status(String role){
			 this.role=role;
		 }
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int album_id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="artist_id")
	private Artist artist;
	
	@Column
	private String status;
	
	@Column
	private String albumName;
	
	@OneToMany(fetch =FetchType.EAGER, cascade = CascadeType.ALL , mappedBy = "track_Id")
	@JsonManagedReference
	private Set<Track> tracks;
	
	public Album() {
		this.status =Status.PENDING.getRole();
		this.tracks = new HashSet<Track>();

	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	public User getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}


	public Set<Track> getTracks() {
		return tracks;
	}


	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
