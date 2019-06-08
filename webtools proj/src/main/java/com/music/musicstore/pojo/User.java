package com.music.musicstore.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
	
	public enum UserRole{
		ADMIN("ADMIN") ,ARTIST("ARTIST"), SUBSCRIBER("SUBSCRIBER");
		private String role;

		public String getRole() {
			return role;
		}
		 UserRole(String role){
			 this.role=role;
		 }
	}
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="firstName")
	private String fname;
	
	@Column(name="lastName")
	private String lname;
	
	@Column(name="email_id")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="status")
	private int status;
	
	//default Constructor
	public User() {
		
	}
	//Parameterised constructor
	public User(String fname, String lname, String email, String password, String role) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.status = 0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
