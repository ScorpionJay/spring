package com.one.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.one.main.restsec.AuthenticationService;
import com.one.service.UserService;
import com.one.vo.InverstVo;
import com.one.vo.ResultVo;
import com.one.vo.UserVo;

@RestController
public class TestController {

/*	@Autowired
	private AuthenticationService authenticationService;*/
	
	 @Autowired  
     private HttpServletRequest request;  
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="test123")
    public String test() {
        return "test";
    }

	@RequestMapping(value="hello")
    public String hello() {
        return "hello";
    }

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "testAdd", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo register( String username,  String password) {
		ResultVo resultVo = new ResultVo();
		userService.addUser(new UserVo(username, password,"admin"));
		return resultVo;
	}
	
	@RequestMapping(value = "user/{username}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getByUsername(@PathVariable String username) {
		ResultVo resultVo = new ResultVo();
		UserVo userVo = userService.getByUsername2(username);
		resultVo.setData(userVo);
//		Integer.parseInt("aaa");
		System.out.println(userVo.toString());
		return resultVo;
	}
	
    @RequestMapping("/inverst")
    @ResponseBody
    public Object test22( @RequestHeader HttpHeaders headers) {
    	// get token from header
    	 System.out.println("from request:" + request.getHeader("Auth-Token"));  
    	//UserDetails currentUser = authenticationService.currentUser();
        List<InverstVo> list = new ArrayList();
        list.add(new InverstVo("1","tyb","10.00","体验宝111","哈哈哈","1年"));
        list.add(new InverstVo("2","uub","12.00","U优宝","好多钱1","1个月"));
        
        if(null != request.getHeader("Auth-Token")){
        	list.add(new InverstVo("3","login test","12.00","登录测试","好多钱1","1个月"));
        }
        
        return list;
    }
	
}
