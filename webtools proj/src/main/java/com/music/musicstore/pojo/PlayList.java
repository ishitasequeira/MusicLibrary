package com.music.musicstore.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity

public class PlayList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int playlist_Id;
	
	@ManyToMany(cascade = CascadeType.REFRESH , fetch=FetchType.EAGER)
	@JoinTable(name = "playlist_track_table",joinColumns = {
			@JoinColumn(name = "playlist_Id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "track_Id") })

	private Set<Track> tracks;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subscriber_id" )
	private Subscriber subscriber;
	
	private String playListName;
	

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public String getPlayListName() {
		return playListName;
	}

	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}

	public int getPlaylist_Id() {
		return playlist_Id;
	}

	public void setPlaylist_Id(int playlist_id) {
		this.playlist_Id = playlist_id;
	}

	public Set<Track> getTracks() {
		return tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

}
