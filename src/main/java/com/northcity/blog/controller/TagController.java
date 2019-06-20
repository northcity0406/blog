package com.northcity.blog.controller;

import com.northcity.blog.entity.Tag;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.service.interfaceDecla.TagService;
import com.northcity.blog.util.IdGenerate;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/a/tag")
@EnableAutoConfiguration
public class TagController {

	private Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TagService tagService;

	@Autowired
	private SyslogService syslogService;

	@GetMapping("/list")
	public List<Tag> findAll(){
		return tagService.findAll();
	}

	@GetMapping("/list/orderByDate")
	public List<Tag> findAllOrderByCreateDate(){
		return tagService.findAllByOrderByCreateTimeDesc();
	}

	@GetMapping("/search")
	public Tag findTagById(@RequestParam(value = "id",required = true) String id){
		return tagService.findTagById(id);
	}


	@GetMapping("/add")
	public void addTag(@RequestParam("name") String name){
		Tag tag = new Tag();
		tag.setAid(1);
		tag.setId(IdGenerate.getTagId().intValue() - 1 + "");
		tag.setName(name);
		tag.setCreateTime(new Timestamp(new Date().getTime()));
		tagService.saveAndFlush(tag);
		logger.info("[增加Tag :]" + tag.toString());
		syslogService.save(SysLogUtil.SaveSyslog("[增加Tag :]" + tag.toString(),request));
	}

	@GetMapping("/modify")
	public void updateTag(@RequestParam("id") String id,
			@RequestParam("name") String name){
		Tag tag = tagService.findTagById(id);
		tag.setName(name);
		tag.setUpdateTime(new Timestamp(new Date().getTime()));
		tagService.saveAndFlush(tag);
		syslogService.save(SysLogUtil.SaveSyslog("[修改Tag :]" + tag.toString(),request));
		logger.info("[修改Tag :]" + tag.toString());
	}


}
