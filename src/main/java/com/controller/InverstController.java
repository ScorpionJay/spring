/*
 * Copyright the original author or authors.
 */

package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.iface.InverstService;
import com.vo.InverstVo;

/**
 * Created by jay on 2016/12/20.
 */
@Controller
@RequestMapping("inverst")
public class InverstController {

	@Autowired(required=false)  
    private HttpServletRequest request; 
	
	@Autowired
	private InverstService inverstService;

	@RequestMapping("/all")
	@ResponseBody
	public Object products() {
		List<InverstVo> list = inverstService.findAll();
		if(null != request.getHeader("Auth-Token")){
	        	list.add(new InverstVo("123","TestLogin", "15.00%", "什么鬼", "3个月"));
	    }
		return list;
	}

}
