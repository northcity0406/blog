package com.northcity.blog.dao;

import com.northcity.blog.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyslogRepository extends JpaRepository<SysLog,Integer> {

	@Override
	<S extends SysLog> S saveAndFlush(S s);
}
