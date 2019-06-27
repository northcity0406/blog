package com.northcity.blog.dao;

import com.northcity.blog.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyslogRepository extends
		JpaRepository<SysLog, Integer>{

	Page<SysLog> findAll(Pageable pageable);

	@Override
	<S extends SysLog> S saveAndFlush(S s);
}
