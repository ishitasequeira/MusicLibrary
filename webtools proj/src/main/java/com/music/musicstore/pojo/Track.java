package com.music.musicstore.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Track {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int track_Id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "album_id")
	@JsonBackReference
	private Album album;
	
	@Column
	private String title;
	
	@Column
	private String fileName;
	 
	 @Transient
	 private String albumId;
	 
	 @Transient
	 MultipartFile file;
	 
	@Transient
	 private MultipartFile music;
	 
	 
	public Track() {
		
	}


	public int getTrack_Id() {
		return track_Id;
	}


	public void setTrack_Id(int track_Id) {
		this.track_Id = track_Id;
	}


	public Album getAlbum() {
		return album;
	}


	public void setAlbum(Album album) {
		this.album = album;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAlbumId() {
		return albumId;
	}


	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public MultipartFile getMusic() {
		return music;
	}


	public void setMusic(MultipartFile music) {
		this.music = music;
	}
	

	 
	 
	 
}
