package com.northcity.blog.controller;

import com.northcity.blog.entity.BlogConfig;
import com.northcity.blog.response.BaseResponse;
import com.northcity.blog.service.interfaceDecla.BlogConfigService;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import com.northcity.blog.util.SysLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
public class BlogConfigController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private BlogConfigService blogConfigService;

	@Autowired
	private SyslogService syslogService;

	@RequestMapping(value = "/a/webConfig/modify",method= {RequestMethod.GET,RequestMethod.POST})
	public BaseResponse<BlogConfig> modifyWebConfig(@RequestParam(value = "blogName",required = true) String blogName,
	                                  @RequestParam(value = "avatar",required = true) String avatar,
	                                  @RequestParam(value = "sign",required = false) String sign,
	                                  @RequestParam(value = "wxpayQrcode",required = false) String wxpayQrcode,
	                                  @RequestParam(value = "alipayQrcode",required = false) String alipayQrcode,
	                                  @RequestParam(value = "github",required = false) String github,
	                                  @RequestParam(value = "viewPassword",required = false) String viewPassword,
	                                  @RequestParam(value = "salt",required = false) String salt){
		BlogConfig b = new BlogConfig();
		b.setBlogName(blogName);
		b.setAvatar(avatar);
		if(sign != null) b.setSign(sign);
		if(wxpayQrcode != null) b.setWxpayQrcode(wxpayQrcode);
		if(alipayQrcode != null) b.setAlipayQrcode(alipayQrcode);
		if(github != null) b.setGithub(github);
		if(viewPassword != null) b.setViewPassword(viewPassword);
		if(salt != null) b.setSalt(salt);
		blogConfigService.saveAndFlush(b);
		logger.info("[修改页面配置:]" + b.toString());
		syslogService.save(SysLogUtil.SaveSyslog("[修改页面配置:]" + b.toString()));
		BaseResponse<BlogConfig> result = new BaseResponse<BlogConfig>();
		result.setSuccess(true);
		result.setMsg("保存成功!");
		result.setData(b);
		return result;
	}
	@RequestMapping(value = "/a/webConfig",method= RequestMethod.GET)
	public BaseResponse<BlogConfig> findWebConfig(){
		List<BlogConfig> blogConfigList = blogConfigService.findAll();
		int size = blogConfigList.size();
		BlogConfig blogConfig = blogConfigList.get(size - 1);
		BaseResponse<BlogConfig> result = new BaseResponse<BlogConfig>();
		result.setSuccess(true);
		result.setMsg("返回成功!");
		result.setData(blogConfig);
		return result;
	}

}
