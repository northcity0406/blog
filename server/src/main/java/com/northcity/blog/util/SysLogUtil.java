package com.northcity.blog.util;

import com.northcity.blog.entity.SysLog;

import java.sql.Timestamp;
import java.util.Date;

public class SysLogUtil {
	public static SysLog SaveSyslog(String action,String content){
		SysLog sysLog = new SysLog();
//		sysLog.setId(IdGenerate.getSyslogId().intValue() - 1);
		sysLog.setTime(new Timestamp(new Date().getTime()));
		sysLog.setAction(action);
		sysLog.setContent(content);
		sysLog.setIp(BlogUtil.getIp());
		return sysLog;
	}

}
