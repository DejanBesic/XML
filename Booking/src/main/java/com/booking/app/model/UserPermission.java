package com.booking.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    
    public UserPermission(Long id, Long user_id, String permission_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.permission_id = permission_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(String permission_id) {
		this.permission_id = permission_id;
	}

	@Column(nullable = false)
    private Long user_id;
    
    @Column(nullable = false)
    private String permission_id;
}
