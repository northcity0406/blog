package com.northcity.blog.dao;

import com.northcity.blog.entity.Friends;
import com.northcity.blog.entity.FriendsPK;
import com.northcity.blog.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyslogRepository extends
		PagingAndSortingRepository<SysLog,Integer>,
		JpaRepository<SysLog, Integer>{

	Page<SysLog> findAll(Pageable pageable);


	@Override
	<S extends SysLog> S saveAndFlush(S s);
}
