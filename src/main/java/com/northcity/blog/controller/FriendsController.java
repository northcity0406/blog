
package com.northcity.blog.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Friends;
import com.northcity.blog.service.interfaceDecla.FriendsService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.concurrent.atomic.*;
import java.util.*;


@RestController
@RequestMapping("/a/friends")
public class FriendsController {
	private static AtomicLong atomicLong = new AtomicLong(0);
	private Logger logger = LoggerFactory.getLogger(FriendsController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private FriendsService friendsService;

	@Autowired
	private SyslogService syslogService;

	@GetMapping("/list")
	public List<Friends> getAllFriends(){
		ObjectMapper objectMapper = new ObjectMapper();
		List<Friends> friendsList = friendsService.findAll();
		String result = "";
		for(Friends w:friendsList){
			try{
				result += objectMapper.writeValueAsString(w);
			}catch(JsonProcessingException e){
				logger.info(e.getMessage());
			}
		}
		logger.info(result);
		return friendsList;
	}

	@GetMapping("/search")
	public Friends findFriendsById(@RequestParam("aid") int aid){
		return friendsService.findFriendsByAid(aid);
	}

	@RequestMapping("/delete")
	public void deleteFriendsByAid(@RequestParam("aid") int aid){
		Friends friends = friendsService.findFriendsByAid(aid);
		friendsService.deleteByAid(aid);
		syslogService.save(SysLogUtil.SaveSyslog("[删除友链 :]" + friends.toString(),request));
		logger.info("[删除友链 :]" + friends.toString());
	}

	@RequestMapping("/add")
	public void addFriends(@RequestParam(value = "name",required = true) String name,
	                       @RequestParam(value = "url",required = true) String url){
		Friends friends = new Friends();
		friends.setAid(1);
		friends.setFriendId(atomicLong.toString() + "");
		atomicLong = new AtomicLong(atomicLong.incrementAndGet());
		friends.setName(name);
		friends.setUrl(url);
		friends.setCreateTime(new Date());
		friends.setStatus(true);
		friends.setTypeId(0);
		friendsService.saveAndUpdate(friends);
		syslogService.save(SysLogUtil.SaveSyslog("[添加友链 :]" + friends.toString(),request));
		logger.info("[添加友链 :]" + friends.toString());
	}

	@RequestMapping("/modify")
	public void modifyFriends(@RequestParam(value = "friendid",required = true) String friendid,
			@RequestParam(value = "name",required = true) String name,
	                       @RequestParam(value = "url",required = true) String url){
		Friends friends = friendsService.findFriendsByFriendId(friendid);
		friends.setName(name);
		friends.setUrl(url);
		friendsService.saveAndUpdate(friends);
		syslogService.save(SysLogUtil.SaveSyslog("[修改友链 :]" + friends.toString(),request));
		logger.info("[修改友链 :]" + friends.toString());
	}
}
