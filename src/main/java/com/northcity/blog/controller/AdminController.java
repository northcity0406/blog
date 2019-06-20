package com.northcity.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.service.interfaceDecla.AdminService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminController {
  private static Logger logger = LoggerFactory.getLogger(AdminController.class);
  @Autowired
  private AdminService adminService;
  @Autowired
  private HttpServletRequest request;

  @Autowired
  private SyslogService syslogService;

  private JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
  StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory);

  @RequestMapping(value = "/a/login",method = RequestMethod.POST)
  public Admin login(
      @RequestParam(value = "username", required = true) String username,
      @RequestParam(value = "password", required = true) String password,
      HttpSession session) {
    Admin admin = adminService.findAdminByUsername(username);
    logger.info(admin.toString());

    if (admin != null && admin.getPassword().equals(password)) {
      session.setAttribute("admin", admin.toString());
      redisTemplate.opsForValue().set("admin", admin.toString());
      String content = "[login success :] username = " + username + ", password = " + password;
      syslogService.save(SysLogUtil.SaveSyslog(content, request));
      return admin;
    }
    String content = "[login failed :] username = " + username + ", password = " + password;
    syslogService.save(SysLogUtil.SaveSyslog(content, request));
    return admin;
  }

  @GetMapping("/a/list")
  public List<Admin> getAdmin() {
    List<Admin> adminList = adminService.findAll();
    ObjectMapper objectMapper = new ObjectMapper();
    String result = "";
    for (Admin x : adminList) {
      try {
        result += objectMapper.writeValueAsString(x);
      } catch (JsonProcessingException e) {
        logger.info(e.toString());
      }
    }
    logger.info(result);
    return adminList;
  }

  @RequestMapping("/a/login/aid")
  public Admin findAdminByAid(@RequestParam("aid") int aid) {
    return adminService.findAdminByAid(aid);
  }

  @RequestMapping("/a/login/aid_uid")
  public Admin findAdminByAidAndUserId(
      @RequestParam("aid") int aid, @RequestParam("userid") String userid) {
    return adminService.findAdminByAidAndUserId(aid, userid);
  }
}
