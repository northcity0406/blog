package com.northcity.blog.util;
import com.northcity.blog.entity.Admin;
import org.springframework.http.HttpHeaders;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

/**
 * 公用工具类
 *
 * @author zbw
 * @since 2017/7/9 22:08
 */
public class BlogUtil {

	/**
	 * 禁止实例化
	 */
	private BlogUtil() {
	}

	/**
	 * 获取session中的users对象
	 *
	 * @return session中的用户
	 */
	public static Admin getCurrentAdmin() {
		HttpSession session = getSession();
		if (null == session) {
			return null;
		}
		return (Admin) session.getAttribute(BlogConst.USER_SESSION_KEY);
	}

	/**
	 * 获取session
	 *
	 * @return {@link HttpSession}
	 */
	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	/**
	 * 获取request
	 *
	 * @return {@link HttpServletRequest}
	 */
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

	/**
	 * 获取域名
	 *
	 * @return 域名字符串
	 */
	public static String getDomain() {
		StringBuffer url = getRequest().getRequestURL();
		return url.delete(url.length() - getRequest().getRequestURI().length(), url.length()).append("/").toString();
	}

	/**
	 * 获取ip
	 *
	 * @return 访问ip
	 */
	public static String getIp() {
		String unknown = "unknown";
		// nginx反向代理IP
		String ip = getRequest().getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取agent
	 *
	 * @return User-Agent信息
	 */
	public static String getAgent() {
		return getRequest().getHeader(HttpHeaders.USER_AGENT);

	}

	/**
	 * 获取字符串md5值(加盐)
	 *
	 * @param str 字符串
	 * @return 加密的字符串
	 */
	public static String getMd5(String str) {
		String base = str + BlogConst.MD5_SLAT;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}
}
