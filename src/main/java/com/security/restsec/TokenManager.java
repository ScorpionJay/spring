package com.security.restsec;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Manages tokens - separated from {@link AuthenticationService},
 * so we can implement and plug various policies.
 */
public interface TokenManager {

	/**
	 * 创建一个token
	 * Creates a new token for the user and returns its {@link TokenInfo}.
	 * It may add it to the token list or replace the previous one for the user. Never returns {@code null}.
	 */
	TokenInfo createNewToken(UserDetails userDetails);

	/**
	 * 删除用户的所有token
	 *  Removes all tokens for user. 
	 *  */
	void removeUserDetails(UserDetails userDetails);

	/**
	 * 删除一个token 
	 * Removes a single token. */
	UserDetails removeToken(String token);

	/**
	 * 根据token查处用户消息 
	 * Returns user details for a token. */
	String getUserDetails(String token);

	/** 
	 * 存放用户信息
	 * Returns a collection with token information for a particular user. */
	Collection<TokenInfo> getUserTokens(UserDetails userDetails);

	/** 
	 * 
	 * Returns a map from valid tokens to users. */
	Map<String, UserDetails> getValidUsers();
}
