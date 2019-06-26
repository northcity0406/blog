package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.BlogConfig;

import java.util.List;

public interface BlogConfigService {

	List<BlogConfig> findAll();

	BlogConfig findBlogConfigById(int id);

	BlogConfig saveAndFlush(BlogConfig b);
}
