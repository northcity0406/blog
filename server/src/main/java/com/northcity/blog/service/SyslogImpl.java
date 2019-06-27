package com.northcity.blog.service;

import com.northcity.blog.dao.SyslogRepository;
import com.northcity.blog.entity.SysLog;
import com.northcity.blog.service.interfaceDecla.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("SyslogService")
public class SyslogImpl implements SyslogService {
	@Autowired
	private SyslogRepository syslogRepository;

	@Override
	public void save(SysLog sysLog) {
		syslogRepository.save(sysLog);
	}

	@Override
	public Page<SysLog> findAll(Pageable pageable) {
		return syslogRepository.findAll(pageable);
	}
}
