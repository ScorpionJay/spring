package com.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.security.restsec.AuthenticationService;
import com.service.iface.FileService;
import com.service.iface.UserService;
import com.util.ExceptionUtil;
import com.vo.ResultVo;


/**
 * File controller
 * 
 * @author Jay
 * @time 2015年6月3日
 */
@RestController
@RequestMapping("v1/file")
public class FileController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileService fileService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	ExceptionUtil exceptionUtil;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo savePicture(@RequestParam(value = "file") MultipartFile file) {
		
		log.info("上传头像");
		ResultVo resultVo = new ResultVo();
		try {
			// 文件类型 大小的判断
			String id = fileService.save(file.getBytes(), file.getOriginalFilename(),file.getContentType());

			UserDetails currentUser = authenticationService.currentUser();
			
			userService.uploadImage(currentUser.getUsername(),id);
			
			resultVo.setData(id);
			resultVo.setMsg("上传成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
	
	
	@RequestMapping(value = "singleSave", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo save(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) {
		log.info("upload file");
		ResultVo resultVo = new ResultVo();
		
		String des = request.getParameter("des");
		if ( file != null ){
			try {
				String id = fileService.save(file.getBytes(), file.getOriginalFilename(),file.getContentType());
				resultVo.setData(id);
				resultVo.setMsg("upload success");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			log.info("Unable to upload. File is empty.");
			exceptionUtil.getException("file.not.exist");
		}
		return resultVo;
	}
	
	
	@RequestMapping(value = "multipleSave", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo multipleSave(@RequestParam(value = "file", required = false) MultipartFile[] files,HttpServletRequest request) {
		log.info("upload file");
		String des = request.getParameter("des");
		
		ResultVo resultVo = new ResultVo();
		if (files != null && files.length >0) {
    		for(int i =0 ;i< files.length; i++){
				try {
					MultipartFile file = files[i];
					String id = fileService.save(file.getBytes(), file.getOriginalFilename(),file.getContentType());
					resultVo.setData(id);
					resultVo.setMsg("upload success");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			log.info("Unable to upload. File is empty.");
			exceptionUtil.getException("file.not.exist");
		}
		return resultVo;
	}
	
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public void getFile(String fileName, HttpServletResponse response){
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] result = fileService.getByName(fileName);
			if(null != result){
				os.write(result); 
			}
		} catch (IOException e) {
			
		}
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public void getFileById( @PathVariable String id, HttpServletResponse response){
		fileService.getById(id,response);
	}
	
	@RequestMapping(value ={"/thumbnail/{id}"}, method = RequestMethod.GET)
	public void getThumbnail( @PathVariable String id, HttpServletResponse response){
		fileService.getThumbnailById(id,response);
	}
	
	@RequestMapping(value ={"/thumbnail"}, method = RequestMethod.GET)
	public void getThumbnail(  HttpServletResponse response){
		fileService.getThumbnailById("",response);
	}
}
