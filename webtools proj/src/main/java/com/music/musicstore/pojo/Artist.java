package com.music.musicstore.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Artist")
@AttributeOverrides({
    @AttributeOverride(name="fname", column=@Column(name="FIRSTNAME")),
    @AttributeOverride(name="lname", column=@Column(name="LASTNAME"))
})
public class Artist extends User {

	@OneToMany(mappedBy="artist",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Album> albums;
	
	public Artist(){
		this.albums = new HashSet<Album>();
	}
	
	public Artist(User user) {
		this.setId(user.getId());
		this.setFname(user.getFname());
		this.setLname(user.getLname());
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setStatus(0);
		this.setRole(UserRole.ARTIST.getRole());
		this.albums = new HashSet<Album>();
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	
}
