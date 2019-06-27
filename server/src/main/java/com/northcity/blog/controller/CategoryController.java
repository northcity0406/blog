package com.northcity.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.entity.Category;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.CategoryService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.http.HttpSession;

@RestController
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SyslogService syslogService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping(value = "/a/category/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> findAll(@RequestParam("all") Boolean all){
		return getResponse(true,"全部分类!");
	}

	@RequestMapping(value = "/w/category/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> findAllList(){
		return getResponse(true,"全部分类!");
	}

	@RequestMapping(value = "/a/category/add",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> addCategory(
			@RequestParam("categoryName") String name,
			HttpSession session) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Admin admin = objectMapper.readValue(stringRedisTemplate.opsForValue().get(BlogConst.USER_SESSION_KEY), Admin.class);
		try{
			Category category = new Category();
			category.setName(name);
			category.setCreateTime(new Timestamp(new Date().getTime()));
			category.setStatus(0);
			category.setArticleCount(0);
			category.setCanDel(0);
			categoryService.saveAndFlush(category);
			logger.info("[Category添加 :]" + category.toString());
			syslogService.save(SysLogUtil.SaveSyslog("[Category添加 :]", category.toString()));
			return getResponse(true,"添加成功!");
		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		return getResponse(false,"添加失败!");
	}

	@RequestMapping(value = "/a/category/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> modifyCategory(
			@RequestParam("categoryId") int id,
			@RequestParam("categoryName") String name){
		Category category = categoryService.findCategoryById(id);
		if(category == null){
			return getResponse(false,"修改失败!");
		}
		try{
			category.setName(name);
			category.setUpdateTime(new Timestamp(new Date().getTime()));
			categoryService.saveAndFlush(category);
			logger.info("[Category修改 :]" + category.toString());
			syslogService.save(SysLogUtil.SaveSyslog("[Category修改 :]", category.toString()));
			return getResponse(true,"修改成功!");
		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		return getResponse(false,"修改失败!");
	}

	@Modifying
	@Transactional
	@RequestMapping(value = "/a/category/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> deleteCategory(
			@RequestParam("categoryId") int id){
		try{
			Category category = categoryService.findCategoryById(id);
			categoryService.deleteById(id);
			logger.info("[Category删除 :]" + category.toString());
			syslogService.save(SysLogUtil.SaveSyslog("[Category删除 :]", category.toString()));
			return getResponse(true,"删除成功!");
		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		return getResponse(false,"删除失败!");
	}

	private BaseResponse<List<Category>> getResponse(boolean success,String msg){
		BaseResponse<List<Category>> response = new BaseResponse<>();
		response.setSuccess(success);
		response.setMsg(msg);
		if(success){
			response.setData(categoryService.findAll());
			return response;
		}
		response.setData(null);
		return response;
	}

}
