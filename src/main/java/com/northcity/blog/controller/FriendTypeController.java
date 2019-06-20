package com.northcity.blog.controller;


import com.northcity.blog.entity.FriendsType;
import com.northcity.blog.service.interfaceDecla.FriendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
