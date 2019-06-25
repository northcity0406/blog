package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface SyslogService {
	SysLog save(SysLog sysLog);

	Page<SysLog> findAll(Pageable pageable);
}
