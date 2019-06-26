package com.northcity.blog.dao;

import com.northcity.blog.entity.BlogConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface BlogConfigRepository extends JpaRepository<BlogConfig,Integer> {

	List<BlogConfig> findAll();

	BlogConfig findBlogConfigById(int id);

	@Override
	<S extends BlogConfig> S saveAndFlush(S s);
}
