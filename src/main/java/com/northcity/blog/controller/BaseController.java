package com.northcity.blog.controller;
import com.northcity.blog.entity.Admin;
import com.northcity.blog.util.BlogConst;
import com.northcity.blog.util.BlogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
public class BaseController {
	@Autowired
	protected HttpServletRequest request;

	protected Admin getCurrentAdmin() {
		return BlogUtil.getCurrentAdmin();
	}
}