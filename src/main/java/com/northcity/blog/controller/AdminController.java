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
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
  private static Logger logger = LoggerFactory.getLogger(AdminController.class);
  @Autowired
  private AdminService adminService;
  @Autowired
  private HttpServletRequest request;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private SyslogService syslogService;

  @RequestMapping(value = "/a/login",method = RequestMethod.POST)
  public BaseResponse<Admin> login(
      @RequestParam(value = "username", required = true) String username,
      @RequestParam(value = "password", required = true) String password,
      HttpSession session) throws JsonProcessingException{
    Admin admin = adminService.findAdminByUsername(username);
    BaseResponse<Admin> result = new BaseResponse<Admin>();
    if (admin != null && admin.getPassword().equals(password)) {
      stringRedisTemplate.opsForValue().set(BlogConst.USER_SESSION_KEY.toString(),
              new ObjectMapper().writeValueAsString(admin));
      logger.info("high :" + new ObjectMapper().writeValueAsString(admin));
      String token = TokenUtil.sign(admin);
      logger.info(token);
      admin.setAccessToken(token);
      admin.setLastLoginTime(new Date());
      logger.info(admin.toString());
      adminService.save(admin);

      String content = "[login success :] username = " + username + ", password = " + password;
      syslogService.save(SysLogUtil.SaveSyslog(content));
      result.setSuccess(true);
      result.setMsg("登录成功");
      result.setData(admin);
      return result;
    }
    String content = "[login failed :] username = " + username + ", password = " + password;
    syslogService.save(SysLogUtil.SaveSyslog(content));
    logger.info(admin.toString());
    result.setSuccess(false);
    result.setMsg("登录失败");
    result.setData(null);
    return result;
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
