package com.northcity.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.entity.Category;
import com.northcity.blog.entity.Tag;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.AdminService;
import com.northcity.blog.service.interfaceDecla.CategoryService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.IdGenerate;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/a/category")
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

	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> findAll(@RequestParam("all") Boolean all){
		BaseResponse<List<Category>> result = new BaseResponse<>();
		result.setSuccess(true);
		result.setMsg("全部分类!");
		result.setData(categoryService.findAll());
		return result;
	}

	@RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Category>> addCategory(
			@RequestParam("categoryName") String name,
			HttpSession session) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		Admin admin = (Admin)objectMapper.readValue(stringRedisTemplate.opsForValue().get(BlogConst.USER_SESSION_KEY),Admin.class);
		BaseResponse<List<Category>> result = new BaseResponse<>();
		try{
			Category category = new Category();
			category.setAid(admin.getAid());
			category.setId(IdGenerate.getCategoryId().intValue() - 1 + "");
			category.setName(name);
			category.setCreateTime(new Date());
			category.setStatus(true);
			category.setArticleCount(0);
			category.setCanDel(true);
			categoryService.saveAndFlush(category);
			logger.info("[Category添加 :]" + category.toString());
			syslogService.save(SysLogUtil.SaveSyslog("[Category添加 :]" + category.toString()));

			result.setSuccess(true);
			result.setMsg("添加成功!");
			result.setData(categoryService.findAll());
			return result;
		}catch (Exception e){
			logger.info(e.getMessage());
		}
		result.setSuccess(true);
		result.setMsg("添加失败!");
		result.setData(categoryService.findAll());
		return result;
	}

	@GetMapping("/modify")
	public Category modifyCategory(
			@RequestParam("id") String id,
			@RequestParam("name") String name){
		Category category = categoryService.findCategoryById(id);
		category.setName(name);
		category.setUpdateTime(new Date());
		categoryService.saveAndFlush(category);
		logger.info("[Category修改 :]" + category.toString());
		syslogService.save(SysLogUtil.SaveSyslog("[Category修改 :]" + category.toString()));
		return category;
	}


	@GetMapping("/delete")
	public Category deleteCategory(
			@RequestParam("id") String id){
		categoryService.deleteById(id);
		Category category = categoryService.findCategoryById(id);
		logger.info("[Category删除 :]" + category.toString());
		syslogService.save(SysLogUtil.SaveSyslog("[Category删除 :]" + category.toString()));
		return category;
	}

}
