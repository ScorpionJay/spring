package com.main.restsec;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Takes care of HTTP request/response pre-processing for login/logout and token check.
 * Login can be performed on any URL, logout only on specified {@link #logoutLink}.
 * All the interaction with Spring Security should be performed via {@link AuthenticationService}.
 * <p>
 * {@link SecurityContextHolder} is used here only for debug outputs. While this class
 * is configured to be used by Spring Security (configured filter on FORM_LOGIN_FILTER position),
 * but it doesn't really depend on it at all.
 */
public final class TokenAuthenticationFilter extends GenericFilterBean {

	@Value("${one.token.header_token}")
	private String HEADER_TOKEN;
	
	@Value("${one.token.header_username}")
	private String HEADER_USERNAME;
	
	@Value("${one.token.header_password}")
	private String HEADER_PASSWORD;

	/**
	 * Request attribute that indicates that this filter will not continue with the chain.
	 * Handy after login/logout, etc.
	 */
	private static final String REQUEST_ATTR_DO_NOT_CONTINUE = "MyAuthenticationFilter-doNotContinue";

	private final String logoutLink;
	private final AuthenticationService authenticationService;

	public TokenAuthenticationFilter(AuthenticationService authenticationService, String logoutLink) {
		this.authenticationService = authenticationService;
		this.logoutLink = logoutLink;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException
	{
		System.out.println(" *** MyAuthenticationFilter.doFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean authenticated = checkToken(httpRequest, httpResponse);

		if (canRequestProcessingContinue(httpRequest) && httpRequest.getMethod().equals("POST")) {
			// If we're not authenticated, we don't bother with logout at all.
			// Logout does not work in the same request with login - this does not make sense,
			// because logout works with token and login returns it.
			if (authenticated) {
				checkLogout(httpRequest);
			}

			// Login works just fine even when we provide token that is valid up to this request,
			// because then we get a new one.
			checkLogin(httpRequest, httpResponse);
		}

		if (canRequestProcessingContinue(httpRequest)) {
			chain.doFilter(request, response);
		}
		System.out.println(" === AUTHENTICATION: " + SecurityContextHolder.getContext().getAuthentication());
	}

	private void checkLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String authorization = httpRequest.getHeader("Authorization");
		String username1 = httpRequest.getParameter(HEADER_USERNAME);
//		String username =  httpRequest.getParameter(HEADER_USERNAME);
//		String password = httpRequest.getParameter(HEADER_PASSWORD);
		String username = httpRequest.getHeader(HEADER_USERNAME);
		String password = httpRequest.getHeader(HEADER_PASSWORD);

		if (authorization != null) {
			checkBasicAuthorization(authorization, httpResponse);
			doNotContinueWithRequestProcessing(httpRequest);
		} else if (username != null && password != null) {
			checkUsernameAndPassword(username, password, httpResponse);
			doNotContinueWithRequestProcessing(httpRequest);
		}
	}

	private void checkBasicAuthorization(String authorization, HttpServletResponse httpResponse) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(authorization);
		if (tokenizer.countTokens() < 2) {
			return;
		}
		if (!tokenizer.nextToken().equalsIgnoreCase("Basic")) {
			return;
		}

		String base64 = tokenizer.nextToken();
		String loginPassword = new String(Base64.decode(base64.getBytes(StandardCharsets.UTF_8)));

		System.out.println("loginPassword = " + loginPassword);
		tokenizer = new StringTokenizer(loginPassword, ":");
		System.out.println("tokenizer = " + tokenizer);
		checkUsernameAndPassword(tokenizer.nextToken(), tokenizer.nextToken(), httpResponse);
	}

	private void checkUsernameAndPassword(String username, String password, HttpServletResponse httpResponse) throws IOException {
		TokenInfo tokenInfo = authenticationService.authenticate(username, password);
		if (tokenInfo != null) {
			httpResponse.setHeader(HEADER_TOKEN, tokenInfo.getToken());
			httpResponse.setHeader("Access-Control-Allow-Origin", "*");// 设置可以访问的域名  *表示任何域名都可以访问
			httpResponse.setHeader("Access-Control-Expose-Headers", "Auth-Token"); // 可以访问的头
			httpResponse.setHeader("Content-Type", "text/plain");
			//httpResponse.setHeader("Access-Control-Allow-Credentials","true");
			OutputStream out = httpResponse.getOutputStream();
			out.write(tokenInfo.getToken().getBytes("UTF-8"));
			// TODO set other token information possible: IP, ...
		} else {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	/** Returns true, if request contains valid authentication token. */
	private boolean checkToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
		String token = httpRequest.getHeader(HEADER_TOKEN);
		if (token == null) {
			return false;
		}

		if (authenticationService.checkToken(token)) {
			System.out.println(" *** " + HEADER_TOKEN + " valid for: " +
				SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return true;
		} else {
			System.out.println(" *** Invalid " + HEADER_TOKEN + ' ' + token);
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			doNotContinueWithRequestProcessing(httpRequest);
		}
		return false;
	}

	private void checkLogout(HttpServletRequest httpRequest) {
		if (currentLink(httpRequest).equals(logoutLink)) {
			String token = httpRequest.getHeader(HEADER_TOKEN);
			// we go here only authenticated, token must not be null
			authenticationService.logout(token);
			doNotContinueWithRequestProcessing(httpRequest);
		}
	}

	// or use Springs util instead: new UrlPathHelper().getPathWithinApplication(httpRequest)
	// shame on Servlet API for not providing this without any hassle :-(
	private String currentLink(HttpServletRequest httpRequest) {
		if (httpRequest.getPathInfo() == null) {
			return httpRequest.getServletPath();
		}
		return httpRequest.getServletPath() + httpRequest.getPathInfo();
	}

	/**
	 * This is set in cases when we don't want to continue down the filter chain. This occurs
	 * for any {@link HttpServletResponse#SC_UNAUTHORIZED} and also for login or logout.
	 */
	private void doNotContinueWithRequestProcessing(HttpServletRequest httpRequest) {
		httpRequest.setAttribute(REQUEST_ATTR_DO_NOT_CONTINUE, "");
	}

	private boolean canRequestProcessingContinue(HttpServletRequest httpRequest) {
		return httpRequest.getAttribute(REQUEST_ATTR_DO_NOT_CONTINUE) == null;
	}
}
