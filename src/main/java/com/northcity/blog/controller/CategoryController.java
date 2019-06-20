package com.northcity.blog.controller;

import com.northcity.blog.entity.Category;
import com.northcity.blog.service.interfaceDecla.AdminService;
import com.northcity.blog.service.interfaceDecla.CategoryService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.IdGenerate;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	private HttpServletRequest request;

	@Autowired
	private SyslogService syslogService;

	@GetMapping
	public List<Category> findAll(){
		return categoryService.findAll();
	}


	@GetMapping("/add")
	public Category addCategory(
			@RequestParam("name") String name,
			HttpSession session){
		Category category = new Category();
		/**
		 * 需要修改
		 */
		category.setAid(1);
		category.setId(IdGenerate.getCategoryId().intValue() - 1 + "");
		category.setName(name);
		category.setCreateTime(new Date());
		categoryService.saveAndFlush(category);

		logger.info("[Category添加 :]" + category.toString());

		syslogService.save(SysLogUtil.SaveSyslog("[Category添加 :]" + category.toString(),request));
		return category;
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
		syslogService.save(SysLogUtil.SaveSyslog("[Category修改 :]" + category.toString(),request));
		return category;
	}


	@GetMapping("/delete")
	public Category deleteCategory(
			@RequestParam("id") String id){
		categoryService.deleteById(id);
		Category category = categoryService.findCategoryById(id);
		logger.info("[Category删除 :]" + category.toString());
		syslogService.save(SysLogUtil.SaveSyslog("[Category删除 :]" + category.toString(),request));
		return category;
	}

}
