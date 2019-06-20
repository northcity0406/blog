package com.northcity.blog.util;

import com.northcity.blog.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SysLogUtil {
	public static SysLog SaveSyslog(String content, HttpServletRequest request){
		SysLog sysLog = new SysLog();
		sysLog.setId(IdGenerate.getSyslogId().intValue() - 1);
		sysLog.setTime(new Date());
		sysLog.setContent(content);
		sysLog.setIp(GetIP.getIpAddr(request));
		return sysLog;
	}

}
