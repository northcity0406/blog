package com.northcity.blog.controller;
import com.northcity.blog.entity.Comments;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.CommentsService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("a/comments")
public class CommentController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private SyslogService syslogService;

	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Comments>> getAllComments() throws IOException {
		BaseResponse<List<Comments>> result = new BaseResponse<>();
		result.setSuccess(true);
		result.setMsg("全部评论！");
		result.setData(commentsService.findAll());
		return result;
	}

	@RequestMapping(value = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<List<Comments>> deleteCommentById(@RequestParam("id") int id) throws IOException {
		commentsService.deleteById(id);
		BaseResponse<List<Comments>> result = new BaseResponse<>();
		result.setSuccess(true);
		result.setMsg("全部评论！");
		result.setData(commentsService.findAll());

		logger.info("[Comment删除 :]" + id);
		syslogService.save(SysLogUtil.SaveSyslog("[Comment删除 :]", id + ""));
		return result;
	}
}

