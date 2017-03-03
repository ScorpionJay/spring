package com.security.secimpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.security.domain.User;
import com.security.restsec.AuthenticationService;
import com.security.restsec.TokenInfo;
import com.security.restsec.TokenManager;
import com.service.iface.UserService;

/**
 * 生成token的token放入redis TODO
 * Service responsible for all around authentication, token checks, etc.
 * This class does not care about HTTP protocol at all.
 */
public class AuthenticationServiceDefault implements AuthenticationService {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	UserService userService;
	
	private final AuthenticationManager authenticationManager;
	private final TokenManager tokenManager;

	public AuthenticationServiceDefault(AuthenticationManager authenticationManager, TokenManager tokenManager) {
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
	}

	@PostConstruct
	public void init() {
		System.out.println(" *** AuthenticationServiceImpl.init with: " + applicationContext);
	}

	@Override
	public TokenInfo authenticate(String login, String password) {
		System.out.println(" *** AuthenticationServiceImpl.authenticate");

		// Here principal=username, credentials=password
		Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);
		try {
			authentication = authenticationManager.authenticate(authentication);
			// Here principal=UserDetails (UserContext in our case), credentials=null (security reasons)
			SecurityContextHolder.getContext().setAuthentication(authentication);

			if (authentication.getPrincipal() != null) {
				UserDetails userContext = (UserDetails) authentication.getPrincipal();
				TokenInfo newToken = tokenManager.createNewToken(userContext);// 这里下手处理token？？？todo
				if (newToken == null) {
					return null;
				}
				return newToken;
			}
		} catch (AuthenticationException e) {
			System.out.println(" *** AuthenticationServiceImpl.authenticate - FAILED: " + e.toString());
		}
		return null;
	}

	@Override
	public boolean checkToken(String token) {
		System.out.println(" *** AuthenticationServiceImpl.checkToken");
		
		// 检验token 这里改成从redis获取判断？？？todo 这里问题较大 需要从新看
		String username = tokenManager.getUserDetails(token);
		User user = userService.getByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
		
			authentication = authenticationManager.authenticate(authentication);
			// Here principal=UserDetails (UserContext in our case), credentials=null (security reasons)
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = null;
			if (authentication.getPrincipal() != null) {
				 userDetails = (UserDetails) authentication.getPrincipal();
			}
		if (userDetails == null) {
			return false;
		}

		Authentication securityToken = new PreAuthenticatedAuthenticationToken(
			userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(securityToken);

		return true;
	}

	@Override
	public void logout(String token) {
		// 删除redis中的token
		UserDetails logoutUser = tokenManager.removeToken(token);
		System.out.println(" *** AuthenticationServiceImpl.logout: " + logoutUser);
		SecurityContextHolder.clearContext();
	}

	@Override
	public UserDetails currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return (UserDetails) authentication.getPrincipal();
	}
}
