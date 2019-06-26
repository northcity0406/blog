package com.northcity.blog.controller;

import com.northcity.blog.entity.ArticleTagMapper;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.AdminService;
import com.northcity.blog.service.interfaceDecla.ArticleTagService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@EnableAutoConfiguration
public class ArticleTagController {
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SyslogService syslogService;

    @Autowired
    private ArticleTagService articleTagService;

    @RequestMapping(value = "/w/article/tagList",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse<List<ArticleTagMapper>> getAllArticleTagMapperById(
            @RequestParam(value = "articleId",required = true) String articleId){
        BaseResponse<List<ArticleTagMapper>> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setMsg("获取文章的标签列表!");
        response.setData(articleTagService.findAllByArticleId(articleId));
        return response;
    }

}
