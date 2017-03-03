package com.repository.redis;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.redis.Token;

@Repository
public interface TokenRepository  extends CrudRepository<Token, String>{
	
	// 没用？？？
	List<Token> findByToken(String token);
	
}
