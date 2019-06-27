package com.northcity.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.*;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.AdminService;
import com.northcity.blog.service.interfaceDecla.ArticleService;
import com.northcity.blog.service.interfaceDecla.ArticleTagService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.IdGenerate;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class ArticleController {
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SyslogService syslogService;

    @RequestMapping(value = "/a/article/list",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse<Page<Article>> getAllArticles(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("status") Byte status,
            @RequestParam("by") String by){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, pageSize, sort);
        return getResponse(true,"所有文章!",pageable,status);
    }

    @RequestMapping(value = "/w/article/list",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse<Page<Article>> getAllArticlesW(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, pageSize, sort);
        return getResponse(true,"所有文章!",pageable,new Byte("0"));
    }

    @Modifying
    @Transactional
    @RequestMapping(value = "/a/article/delete",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse<Page<Article>> deleteFriendsByAid(
            @RequestParam("id") int id){
        Article article = articleService.findArticleById(id);
        try{
            article.setStatus(new Byte("1"));
            articleService.saveAndFlush(article);
            syslogService.save(SysLogUtil.SaveSyslog("[删除友链 :]", article.getId() + ""));
            logger.info("[删除友链 :]" + article.getId());
        }catch (Exception e){
            logger.info(e.getMessage());
        }finally {
            Sort sort = new Sort(Sort.Direction.DESC, "friendId");
            Pageable pageable = new PageRequest(1, 15, sort);
            return getResponse(true,"删除友链成功!",pageable,new Byte("0"));
        }
    }

    @Modifying
    @Transactional
    @RequestMapping(value = "/a/article/save",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse<Page<Article>> saveArticle(
            @RequestParam(value = "title",required = true) String title,
            @RequestParam(value = "cover",required = true) String cover,
            @RequestParam(value = "subMessage",required = true) String subMessage,
            @RequestParam(value = "isEncrypt",required = true) int isEncrypt,
            @RequestParam(value = "content",required = true) String content,
            @RequestParam(value = "htmlContent",required = true) String htmlContent,
            @RequestParam(value = "category",required = true) int categoryId,
            @RequestParam(value = "tags",required = true) String tags
    ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Admin admin = objectMapper.readValue(stringRedisTemplate.opsForValue().get(BlogConst.USER_SESSION_KEY),Admin.class);
        try{
            Article article = new Article();
            article.setUserId(admin.getId());
            article.setTitle(title);
            article.setCategoryId(categoryId);
            article.setStatus(0);
            article.setContent(content);
            article.setHtmlContent(htmlContent);
            article.setCover(cover);
            article.setPageview(0);
            article.setCreateTime(new Timestamp(new Date().getTime()));
            article.setSubMessage(subMessage);
            article.setIsEncrypt(isEncrypt);
            Article article1 = articleService.saveAndFlush(article);
            syslogService.save(SysLogUtil.SaveSyslog("[添加文章 :]", article1.getId() + ""));
            logger.info("[添加文章 :]",article.getId() + "");
        }catch (Exception e){
            logger.info(e.getMessage());
        }finally {
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            Pageable pageable = new PageRequest(1, 15, sort);
            return getResponse(true,"添加友链成功!",pageable,0);
        }
    }


    private BaseResponse<Page<Article>> getResponse(boolean success,String msg,Pageable pageable,int status){
        BaseResponse<Page<Article>> response = new BaseResponse<>();
        response.setSuccess(success);
        response.setMsg(msg);
        if(success){
            response.setData(articleService.findAllByStatus(pageable,status));
            return response;
        }
        response.setData(null);
        return response;
    }
}