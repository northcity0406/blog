package com.northcity.blog.controller;
import com.northcity.blog.entity.Friends;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.FriendsService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;


@RestController
public class FriendsController {
	private Logger logger = LoggerFactory.getLogger(FriendsController.class);

	@Autowired
	private FriendsService friendsService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private SyslogService syslogService;

	/**
	 * 获取所有友链
	 * @return
	 */
	@RequestMapping(value = "/w/friends/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Friends>> getAllFriendsBlog(){
		BaseResponse<List<Friends>> response = new BaseResponse<>();
		response.setSuccess(true);
		response.setMsg("全部友链!");
		response.setData(friendsService.findAll());
		return response;
	}

	/**
	 * 获取所有友链
	 * @return
	 */
	@RequestMapping(value = "/a/friends/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> getAllFriends(
			@RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, pageSize, sort);
		return getResponse(true,"所有友链!",pageable);
	}

	/**
	 * 删除友链
	 * @param friendId 友链ID
	 * @return
	 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/a/friends/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> deleteFriendsByAid(@RequestParam("friendId") int friendId){
		Friends friends = friendsService.findFriendsById(friendId);
		try{
			friendsService.deleteByFriendId(friends);
			syslogService.save(SysLogUtil.SaveSyslog("[删除友链 :]", friends.toString()));
			logger.info("[删除友链 :]" + friends.toString());
		}catch (Exception e){
			logger.info(e.getMessage());
		}finally {
			Sort sort = new Sort(Sort.Direction.DESC, "id");
			Pageable pageable = new PageRequest(1, 15, sort);
			return getResponse(true,"添加友链成功!",pageable);
		}
	}

	/**
	 * 添加友链
	 * @param name 友链名字
	 * @param url 友链链接
	 * @param typeId 友链类型
	 * @return
	 * @throws IOException
	 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/a/friends/add",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> addFriends(
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "url",required = true) String url,
			@RequestParam(value = "typeId",required = true) int typeId
			) throws IOException {
		logger.info(name + "\t" + url + "\t" + typeId);
		try{
			Friends friends = new Friends();
			friends.setName(name);
			friends.setUrl(url);
			friends.setCreateTime(new Timestamp(new Date().getTime()));
			friends.setStatus(0);
			friends.setTypeId(typeId);
			friendsService.saveAndUpdate(friends);
			syslogService.save(SysLogUtil.SaveSyslog("[添加友链 :]", friends.toString()));
			logger.info("[添加友链 :]" + friends.toString());
		}catch (Exception e){
			logger.info(e.getMessage());
		}finally {
			Sort sort = new Sort(Sort.Direction.DESC, "id");
			Pageable pageable = new PageRequest(1, 15, sort);
			return getResponse(true,"添加友链成功!",pageable);
		}
	}

	/**
	 * 修改友链
	 * @param friendid 友链ID
	 * @param typeId 友链类型
	 * @param name 友链名字
	 * @param url 友链链接
	 * @return
	 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/a/friends/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> modifyFriends(
			@RequestParam(value = "friendId",required = true) int friendid,
			@RequestParam(value = "typeId",required = true) int typeId,
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "url",required = true) String url){
		Friends friends = friendsService.findFriendsById(friendid);
		try{
			friends.setName(name);
			friends.setUrl(url);
			friends.setTypeId(typeId);
			friendsService.saveAndUpdate(friends);
			syslogService.save(SysLogUtil.SaveSyslog("[修改友链 :]", friends.toString()));
			logger.info("[修改友链 :]" + friends.toString());
		}catch (Exception e){
			logger.info(e.getMessage());
		}finally {
			Sort sort = new Sort(Sort.Direction.DESC, "id");
			Pageable pageable = new PageRequest(1, 15, sort);
			return getResponse(true,"添加友链成功!",pageable);
		}
	}

	private BaseResponse<Page<Friends>> getResponse(boolean success,String msg,Pageable pageable){
		BaseResponse<Page<Friends>> response = new BaseResponse<>();
		response.setSuccess(success);
		response.setMsg(msg);
		if(success){
			response.setData(friendsService.findAll(pageable));
			return response;
		}
		response.setData(null);
		return response;
	}
}
