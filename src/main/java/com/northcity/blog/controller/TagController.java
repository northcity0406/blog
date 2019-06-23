package com.northcity.blog.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.entity.Tag;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.service.interfaceDecla.TagService;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.IdGenerate;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/a/tag")
@EnableAutoConfiguration
public class TagController{

	private Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagService tagService;

	@Autowired
	private SyslogService syslogService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> findAll(@RequestParam("all") Boolean all){
		BaseResponse<List<Tag>> result = new BaseResponse<>();
		result.setSuccess(true);
		result.setMsg("全部标签!");
		result.setData(tagService.findAll());
		return result;
	}

	@GetMapping("/list/orderByDate")
	public List<Tag> findAllOrderByCreateDate(){
		return tagService.findAllByOrderByCreateTimeDesc();
	}

	@GetMapping("/search")
	public Tag findTagById(@RequestParam(value = "id",required = true) String id){
		return tagService.findTagById(id);
	}


	@RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> addTag(@RequestParam("tagName") String name, HttpSession session) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Admin admin = (Admin)objectMapper.readValue(stringRedisTemplate.opsForValue().get(BlogConst.USER_SESSION_KEY),Admin.class);
		BaseResponse<List<Tag>> result = new BaseResponse<>();
		try{
			Tag tag = new Tag();
			tag.setAid(admin.getAid());
			tag.setId(IdGenerate.getTagId().intValue() - 1 + "");
			tag.setName(name);
			tag.setCreateTime(new Timestamp(new Date().getTime()));
			tag.setStatus(true);
			tag.setArticleCount(0);
			tagService.saveAndFlush(tag);
			logger.info("[增加Tag :]" + tag.toString());
			syslogService.save(SysLogUtil.SaveSyslog("[增加Tag :]" + tag.toString()));

			result.setSuccess(true);
			result.setMsg("添加成功!");
			result.setData(tagService.findAll());
			return result;

		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		result.setSuccess(false);
		result.setMsg("添加失败!");
		result.setData(null);
		return result;
	}

	@GetMapping("/modify")
	public void updateTag(@RequestParam("id") String id,
			@RequestParam("name") String name){
		Tag tag = tagService.findTagById(id);
		tag.setName(name);
		tag.setUpdateTime(new Timestamp(new Date().getTime()));
		tagService.saveAndFlush(tag);
		syslogService.save(SysLogUtil.SaveSyslog("[修改Tag :]" + tag.toString()));
		logger.info("[修改Tag :]" + tag.toString());
	}
}
