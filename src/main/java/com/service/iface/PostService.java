package com.service.iface;

import java.util.List;

import com.vo.PostAllVo;
import com.vo.PostVo;

/**
 * @author jay
 * @since 2016-7-7
 */
public interface PostService {

	/**
	 * add
	 * 
	 * @param articleVo
	 */
	void add(PostVo vo);

	/**
	 * get by Id
	 * 
	 * @param id
	 * @return
	 */
	PostVo getById(String id);

	/**
	 * get sort data by date
	 * 
	 * @param date
	 * @return
	 */
	List<PostVo> get(String userId, String date);

	/**
	 * delete
	 * 
	 * @param id
	 */
	void deleteById(String id);

	/**
	 * get sort data by date
	 * 
	 * @param date
	 * @return
	 */
	List<PostAllVo> getAll(String date);

}
