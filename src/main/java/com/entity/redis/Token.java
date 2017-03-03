package com.entity.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.security.core.userdetails.UserDetails;

@RedisHash("token")
public class Token {

	@Id
	String token;

	private UserDetails userDetails;

	@TimeToLive
	private Long expiration;

	public Long getExpiration() {
		return expiration;
	}

	public Token() {
		super();
	}

	public Token(String token, UserDetails userDetails, Long expiration) {
		super();
		this.token = token;
		this.userDetails = userDetails;
		this.expiration = expiration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

}
