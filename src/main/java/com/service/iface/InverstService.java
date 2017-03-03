/*
 * Copyright the original author or authors.
 */

package com.service.iface;

import java.util.List;

import com.vo.InverstVo;

/**
 * 投资产品接口
 * 
 * @author jay
 * @since 2017/1/23
 */
public interface InverstService {

	/**
	 * 新增
	 * @param inverstVo
	 */
	void add(InverstVo inverstVo);

	/**
	 * 查看所有
	 * @return
	 */
	List<InverstVo> findAll();

	List<InverstVo> testJpa();

	List<InverstVo> testMybaties();

	List<InverstVo> testMybaties2();

	void save(InverstVo vo);

}
