package com.northcity.blog.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.AdminService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.SysLogUtil;
import com.northcity.blog.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.*;

@RestController
@EnableAutoConfiguration
public class AdminController {
  private static Logger logger = LoggerFactory.getLogger(AdminController.class);
  @Autowired
  private AdminService adminService;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private SyslogService syslogService;

  /**
   * API:/a/login
   * @param username 用户名
   * @param password 密码
   * @return  返回当前登录的用户
   * @throws JsonProcessingException
   */

  @RequestMapping(value = "/a/login",method = {RequestMethod.POST,RequestMethod.GET})
  public BaseResponse<Admin> login(
      @RequestParam(value = "username", required = true) String username,
      @RequestParam(value = "password", required = true) String password) throws JsonProcessingException{
    Admin admin = adminService.findAdminByUsername(username);
    BaseResponse<Admin> result= new BaseResponse<>();

    if (admin != null && admin.getPassword().equals(password)) {
      //登陆成功
      stringRedisTemplate.opsForValue().set(BlogConst.USER_SESSION_KEY,
              new ObjectMapper().writeValueAsString(admin));
//      admin.setPassword("*****"); //隐藏用户密码
      logger.info(new ObjectMapper().writeValueAsString(admin));
      String token = TokenUtil.sign(admin); //获取用户Token
      admin.setToken(token);
      admin.setLastLoginTime(new Timestamp(new Date().getTime()));
      logger.info(admin.toString());
      adminService.save(admin);
      syslogService.save(SysLogUtil.SaveSyslog("login success",admin.toString()));
      result.setSuccess(true);
      result.setMsg("登录成功");
      result.setData(admin);
      return result;
    }
    syslogService.save(SysLogUtil.SaveSyslog("login failed",""));
    result.setSuccess(false);
    result.setMsg("登录失败");
    result.setData(null);
    return result;
  }

  /**
   * 返回所有的用户
   * @return
   */


  @GetMapping("/a/list")
  public BaseResponse<List<Admin>> getAdmin() {
    return getResponse(true,"所有用户");
  }

  private BaseResponse<List<Admin>> getResponse(boolean success,String msg){
    BaseResponse<List<Admin>> response = new BaseResponse<>();
    response.setSuccess(success);
    response.setMsg(msg);
    if(success){
      response.setData(adminService.findAll());
      return response;
    }
    response.setData(null);
    return response;
  }
}
