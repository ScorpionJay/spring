package com.security.secimpl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;

import com.entity.redis.Token;
import com.security.restsec.TokenInfo;
import com.security.restsec.TokenManager;
import com.service.iface.TokenService;

/**
 * token管理，之前是使用的map，这里交个redis处理todo
 */
public class TokenManagerSingle implements TokenManager {

	@Autowired
	TokenService tokenService;

	// 改成redis
	private Map<String, UserDetails> validUsers = new HashMap<>();

	/**
	 * This maps system users to tokens because equals/hashCode is delegated to
	 * User entity. This can store either one token or list of them for each
	 * user, depending on what you want to do. Here we store single token, which
	 * means, that any older tokens are invalidated.
	 */
	// private Map<UserDetails, TokenInfo> tokens = new HashMap<>();

	@Override
	public TokenInfo createNewToken(UserDetails userDetails) {
		String token;
		do {
			token = generateToken(userDetails.getUsername());
		} while (validUsers.containsKey(token));
		
		Token t = new Token();
		t.setId(token);
		t.setUsername(((UserContext)userDetails).getUsername());
		t.setExpiration(300L);
		tokenService.save(t);
		

		TokenInfo tokenInfo = new TokenInfo(token, userDetails);
//		removeUserDetails(userDetails);
//		UserDetails previous = validUsers.put(token, userDetails);
//		if (previous != null) {
//			System.out.println(" *** SERIOUS PROBLEM HERE - we generated the same token (randomly?)!");
//			return null;
//		}
		// tokens.put(userDetails, tokenInfo);

		return tokenInfo;
	}

	/**
	 * 生成token策略
	 */
	private String generateToken(String username) {
		byte[] tokenBytes = new byte[32];
		new SecureRandom().nextBytes(tokenBytes);
		return new String(Base64.encode(tokenBytes), StandardCharsets.UTF_8);
	}

	@Override
	public void removeUserDetails(UserDetails userDetails) {
		// TokenInfo token = tokens.remove(userDetails);
		// if (token != null) {
		// validUsers.remove(token.getToken());
		// }
	}

	@Override
	public UserDetails removeToken(String token) {
		UserDetails userDetails = validUsers.remove(token);
		if (userDetails != null) {
			// tokens.remove(userDetails);
		}
		return userDetails;
	}

	@Override
	public String getUserDetails(String token) {
		Token t =  tokenService.get(token);
		if( t == null ){
			return null;
		}
		return t.getUsername();
	}

	@Override
	public Collection<TokenInfo> getUserTokens(UserDetails userDetails) {
		return null;// Arrays.asList(tokens.get(userDetails));
	}

	@Override
	public Map<String, UserDetails> getValidUsers() {
		return Collections.unmodifiableMap(validUsers);
	}
}
