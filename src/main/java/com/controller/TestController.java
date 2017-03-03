package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.iface.UserService;
import com.vo.ResultVo;
import com.vo.UserVo;

@RestController
public class TestController {

/*	@Autowired
	private AuthenticationService authenticationService;*/
	
	 @Autowired(required=false)  
     private HttpServletRequest httpServletRequest; 
	
	@Autowired
	private UserService userService;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
//	 
//	@Autowired
//	private StringRedisTemplate template;

	
	@RequestMapping(value="test123")
    public String test() {
		 listOps.leftPush("123", "test");
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
	
/*    @RequestMapping("/inverst")
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
    }*/
	
	
	
}
