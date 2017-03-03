package com.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.App;
import com.service.iface.InverstService;
import com.vo.InverstVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class InverstServiceImplTest {

	private Logger log = LoggerFactory.getLogger(InverstServiceImplTest.class);

	@Autowired
	InverstService inverstService;

	@Test
	public void testAdd() {
//		InverstVo inverstVo = new InverstVo("基金", "4.00%", "什么鬼", "3个月");
		InverstVo inverstVo = new InverstVo("股票3", "15.00%", "什么鬼", "3个月");
		inverstService.add(inverstVo);
	}

	@Test
	public void testFindAll() {
		List<InverstVo> list =  inverstService.findAll();
		for (InverstVo inverstVo : list) {
			log.info(inverstVo.toString());
		}
	}

	@Test
	public void testTestJpa() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestMybaties() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestMybaties2() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

}
