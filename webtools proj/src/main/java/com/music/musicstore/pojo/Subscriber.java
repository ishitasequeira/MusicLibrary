package com.music.musicstore.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Subscriber extends User {

	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL ,mappedBy="subscriber")
	private Set<PlayList> playList;

	public Subscriber(){}
	
	public Subscriber(User user) {
		this.setId(user.getId());
		this.setFname(user.getFname());
		this.setLname(user.getLname());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setStatus(0);
		this.setRole(UserRole.SUBSCRIBER.getRole());
		this.playList = new HashSet<PlayList>();
	}

	public Set<PlayList> getPlayList() {
		return playList;
	}

	public void setPlayList(Set<PlayList> playList) {
		this.playList = playList;
	}
	
	
}
