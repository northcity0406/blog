
package com.northcity.blog.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.entity.Friends;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.FriendsService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.IdGenerate;
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
import java.util.*;


@RestController
@RequestMapping("/a/friends")
public class FriendsController {
	private Logger logger = LoggerFactory.getLogger(FriendsController.class);

	@Autowired
	private FriendsService friendsService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private SyslogService syslogService;

	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> getAllFriends(
			@RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize){
		Sort sort = new Sort(Sort.Direction.DESC, "friendId");
		Pageable pageable = new PageRequest(page, pageSize, sort);
		return getResponse(true,"所有友链!",pageable);
	}

	@Modifying
	@Transactional
	@RequestMapping(value = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> deleteFriendsByAid(@RequestParam("friendId") String friendId){
		Friends friends = friendsService.findFriendsByFriendId(friendId);
		try{
			friendsService.deleteByFriendId(friends);
			syslogService.save(SysLogUtil.SaveSyslog("[删除友链 :]" + friends.toString()));
			logger.info("[删除友链 :]" + friends.toString());
		}catch (Exception e){
			logger.info(e.getMessage());
		}finally {
			Sort sort = new Sort(Sort.Direction.DESC, "friendId");
			Pageable pageable = new PageRequest(1, 15, sort);
			return getResponse(true,"添加友链成功!",pageable);
		}
	}

	@Modifying
	@Transactional
	@RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> addFriends(
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "url",required = true) String url,
			@RequestParam(value = "typeId",required = true) int typeId
			) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Admin admin = objectMapper.readValue(stringRedisTemplate.opsForValue().get(BlogConst.USER_SESSION_KEY),Admin.class);

		try{
			Friends friends = new Friends();
			friends.setAid(admin.getAid());
			friends.setFriendId(IdGenerate.getFriendId().intValue() - 1 + "");
			friends.setName(name);
			friends.setUrl(url);
			friends.setCreateTime(new Date());
			friends.setStatus(false);
			friends.setTypeId(typeId);
			friendsService.saveAndUpdate(friends);
			syslogService.save(SysLogUtil.SaveSyslog("[添加友链 :]" + friends.toString()));
			logger.info("[添加友链 :]" + friends.toString());
		}catch (Exception e){
			logger.info(e.getMessage());
		}finally {
			Sort sort = new Sort(Sort.Direction.DESC, "friendId");
			Pageable pageable = new PageRequest(1, 15, sort);
			return getResponse(true,"添加友链成功!",pageable);
		}
	}

	@Modifying
	@Transactional
	@RequestMapping(value = "/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<Page<Friends>> modifyFriends(
			@RequestParam(value = "friendId",required = true) String friendid,
			@RequestParam(value = "typeId",required = true) int typeId,
			@RequestParam(value = "name",required = true) String name,
			@RequestParam(value = "url",required = true) String url){
		Friends friends = friendsService.findFriendsByFriendId(friendid);
		try{
			friends.setName(name);
			friends.setUrl(url);
			friends.setTypeId(typeId);
			friendsService.saveAndUpdate(friends);
			syslogService.save(SysLogUtil.SaveSyslog("[修改友链 :]" + friends.toString()));
			logger.info("[修改友链 :]" + friends.toString());
		}catch (Exception e){
			logger.info(e.getMessage());
		}finally {
			Sort sort = new Sort(Sort.Direction.DESC, "friendId");
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
