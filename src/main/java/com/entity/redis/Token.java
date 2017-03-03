package com.entity.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.secimpl.UserContext;

@RedisHash("token")
public class Token {

	@Id
	String id;

	String  username;

	@TimeToLive
	private Long expiration;

	public Token() {
		super();
	}

	public Token(String id, String username, Long expiration) {
		super();
		this.id = id;
		this.username = username;
		this.expiration = expiration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", username=" + username + ", expiration=" + expiration + "]";
	}

	

}
