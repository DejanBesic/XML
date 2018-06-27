package com.booking.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	//@ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @Transient
	protected List<User> users;
	
	public Permission() {
		// TODO Auto-generated constructor stub
	}

	public Permission(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUser() {
		return users;
	}

	public void setUser(List<User> users) {
		this.users = users;
	}
	
}