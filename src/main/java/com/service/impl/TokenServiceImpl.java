package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.redis.Token;
import com.repository.redis.TokenRepository;
import com.service.iface.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public void save(Token token) {
		tokenRepository.save(token);
	}

	@Override
	public Token get(String token) {
//		List<Token> list =  tokenRepository.findByToken(token);
		return tokenRepository.findOne(token);
	}

	@Override
	public void delete(String token) {
		tokenRepository.delete(token);

	}

	@Override
	public void update(Token token) {
//		tokenRepository.
	}

}
