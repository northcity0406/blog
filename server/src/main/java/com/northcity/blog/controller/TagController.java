package com.northcity.blog.controller;
import com.northcity.blog.entity.Tag;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.service.interfaceDecla.TagService;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class TagController{

	private Logger logger = LoggerFactory.getLogger(TagController.class);

	@Autowired
	private TagService tagService;

	@Autowired
	private SyslogService syslogService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * API:/a/tag/list
	 *获取所有的标签!
	 * @param all 所有的标签
	 * @return
	 */
	@RequestMapping(value = "/a/tag/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> findAll(@RequestParam("all") Boolean all){
		return getResponse(true,"所有标签!");
	}

	/**
	 *API:/w/tag/list
	 * 获取所有的标签
	 * @return
	 */
	@RequestMapping(value = "/w/tag/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> findAllList(){
		return getResponse(true,"所有标签!");
	}

	/**
	 * 添加一个标签！
	 * @param name 标签的名字
	 * @return
	 * @throws IOException
	 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/a/tag/add",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> addTag(@RequestParam("tagName") String name) throws IOException {
		logger.info(name);
		try{
			Tag tag = new Tag();
			tag.setName(name);
			tag.setCreateTime(new Timestamp(new Date().getTime()));
			tag.setStatus(0);
			tag.setArticleCount(0);
			tagService.saveAndFlush(tag);
			logger.info("[增加Tag :]" + tag.toString());

			syslogService.save(SysLogUtil.SaveSyslog("增加标签",tag.toString()));
			return getResponse(true,"添加成功!");
		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		logger.info("[增加Tag失败 :]");
		syslogService.save(SysLogUtil.SaveSyslog("增加标签失败",""));
		return getResponse(false,"添加失败!");
	}

	/**
	 * 修改标签!
	 * @param id 标签的ID
	 * @param name 标签的名字
	 * @return
	 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/a/tag/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> modifyTag(@RequestParam("tagId") int id,
			@RequestParam("tagName") String name){
		Tag tag = tagService.findTagById(id);
		if(tag == null){
			return getResponse(false,"修改失败!");
		}
		try{
			tag.setName(name);
			tag.setUpdateTime(new Timestamp(new Date().getTime()));
			tagService.saveAndFlush(tag);
			syslogService.save(SysLogUtil.SaveSyslog("[修改Tag :]", tag.toString()));
			logger.info("[修改Tag :]" + tag.toString());
			return getResponse(true,"修改成功!");
		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		logger.info("[修改Tag失败 :]" + "");
		syslogService.save(SysLogUtil.SaveSyslog("[修改Tag失败 :]", ""));
		return getResponse(false,"修改失败!");
	}


	/**
	 * 删除标签!
	 * @param id 标签ID
	 * @return
	 */
	@Modifying
	@Transactional
	@RequestMapping(value = "/a/tag/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Tag>> deleteTag(@RequestParam("tagId") int id){
		try{
			Tag tag = tagService.findTagById(id);
			tagService.deleteById(id);
			logger.info("[Category删除 :]" + tag.toString());
			syslogService.save(SysLogUtil.SaveSyslog("[Category删除 :]", tag.toString()));
			return getResponse(true,"删除成功!");
		}catch (NullPointerException e){
			logger.info(e.getMessage());
		}
		logger.info("[Category删除失败 :]");
		syslogService.save(SysLogUtil.SaveSyslog("[Category删除失败 :]", ""));
		return getResponse(false,"删除失败!");
	}

	private BaseResponse<List<Tag>> getResponse(boolean success,String msg){
		BaseResponse<List<Tag>> response = new BaseResponse<>();
		response.setSuccess(success);
		response.setMsg(msg);
		if(success){
			response.setData(tagService.findAll());
			return response;
		}
		response.setData(null);
		return response;
	}
}
