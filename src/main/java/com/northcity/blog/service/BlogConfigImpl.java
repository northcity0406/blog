package com.northcity.blog.service;

import com.northcity.blog.dao.BlogConfigRepository;
import com.northcity.blog.entity.BlogConfig;
import com.northcity.blog.service.interfaceDecla.BlogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BlogConfigService")
public class BlogConfigImpl implements BlogConfigService {

	@Autowired
	private BlogConfigRepository blogConfigRepository;

	@Override
	public List<BlogConfig> findAll() {
		return blogConfigRepository.findAll();
	}

	@Override
	public BlogConfig findBlogConfigById(int id) {
		return blogConfigRepository.findBlogConfigById(id);
	}

	@Override
	public BlogConfig saveAndFlush(BlogConfig b) {
		return blogConfigRepository.saveAndFlush(b);
	}
}
