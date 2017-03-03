package com.service.iface;

import com.entity.redis.Token;

public interface TokenService {

	void save(Token token);

	Token get(String token);

	void delete(String token);

	void update(Token token);

}
