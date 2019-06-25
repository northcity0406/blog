package com.northcity.blog.controller;


import com.northcity.blog.entity.FriendsType;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.FriendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.annotation.Resource;

@RestController
public class FriendTypeController {
	@Autowired
	@Resource
	private FriendTypeService friendTypeService;

	@RequestMapping({"/a/friendtype"})
	public FriendsType getFriendTypeById(@RequestParam("id") int id){
		return friendTypeService.getFriendsTypeById(id);
	}

	@RequestMapping(value = "/a/friends/typeList",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<FriendsType>> findAllFriendType(){
		BaseResponse<List<FriendsType>> response = new BaseResponse<>();
		response.setSuccess(true);
		response.setMsg("全部友链类型!");
		response.setData(friendTypeService.findAll());
		return response;
	}

}
