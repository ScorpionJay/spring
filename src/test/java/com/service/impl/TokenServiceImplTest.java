package com.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.App;
import com.entity.redis.Token;
import com.service.iface.InverstService;
import com.service.iface.TokenService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class TokenServiceImplTest {

	@Autowired
	TokenService tokenService;
	
	@Test
	public void testSave() {
		Token token = new Token();
		//token.setToken("testid");
		token.setExpiration(50l);
		tokenService.save(token);
	}

	@Test
	public void testGet() {
//		Token token =  tokenService.get("5b765414-09d8-421a-ae00-9058eaac443a");
		Token token =  tokenService.get("PUgayW46ZPig2XU7ehNn2l9r/txROmRhheOQdkM73zA=");
		System.out.println(token);
	}

	@Test
	public void testDelete() {
		tokenService.delete("testid");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
