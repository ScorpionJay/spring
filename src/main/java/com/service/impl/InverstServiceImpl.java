package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.entity.mongo.Inverst;
import com.service.iface.InverstService;
import com.vo.InverstVo;

/**
 * 投资产品接口实现
 * 
 * @author jay
 * @since 2017/1/23
 */
@Service
public class InverstServiceImpl implements InverstService {

	private static final Logger logger = LoggerFactory.getLogger(InverstServiceImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@CacheEvict(value = { "inversts"}, allEntries = true)  
	@Override
	public void add(InverstVo inverstVo) {
		 Inverst inverst = new Inverst();
		 inverst.setRate(inverstVo.getRate());
		 inverst.setDescription(inverstVo.getDescription());
		 inverst.setType(inverstVo.getType());
		 inverst.setTypeTerm(inverstVo.getTypeTerm());
	     mongoTemplate.save(inverst);
	}

	@Cacheable(value = "inversts",keyGenerator = "wiselyKeyGenerator") 
	@Override
	public List<InverstVo> findAll() {
		logger.info("验证有没有走redis");
		List<InverstVo> inverstVoList = new ArrayList<>(); 
		List<Inverst> list = mongoTemplate.findAll(Inverst.class);
		if(!list.isEmpty()){
			for(Inverst inverst : list){
				inverstVoList.add(new InverstVo(inverst.getId(), inverst.getType(), inverst.getRate(), inverst.getDescription(), inverst.getTypeTerm()));
			}
		}
		return inverstVoList;
	}

	@Override
	public List<InverstVo> testJpa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InverstVo> testMybaties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InverstVo> testMybaties2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(InverstVo vo) {
		// TODO Auto-generated method stub

	}

}
