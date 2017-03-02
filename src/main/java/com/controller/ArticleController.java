package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.restsec.AuthenticationService;
import com.service.iface.ArticleService;
import com.service.iface.UserService;
import com.vo.ArticleVo;
import com.vo.ResultVo;
import com.vo.UserVo;

/**
 * @author jay
 * @since 2016年6月30日
 */
@Controller
@RequestMapping("v1/article")
public class ArticleController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleService articleService;
	
	
	/**
	 * get user info by username
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getByUsername(String type,String date,Integer pageSize) {
		ResultVo resultVo = new ResultVo();
		List<ArticleVo> lsit = articleService.getByType(type, date , pageSize);
		resultVo.setData(lsit);
		log.info(resultVo.toString());
		return resultVo;
	}
	
	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getPosts(String date) {
		ResultVo resultVo = new ResultVo();
		List<ArticleVo> lsit = articleService.getByType( date);
		resultVo.setData(lsit);
		log.info(resultVo.toString());
		return resultVo;
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getPost(@PathVariable String id) {
		ResultVo resultVo = new ResultVo();
		ArticleVo vo = articleService.getById( id );
		resultVo.setData(vo);
		log.info(resultVo.toString());
		return resultVo;
	}
	
}
