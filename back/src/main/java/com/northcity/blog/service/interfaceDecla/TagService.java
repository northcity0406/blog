package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Tag;

import java.util.*;

public interface TagService {
	List<Tag> findAll();

	Tag findTagById(String id);

	List<Tag> findAllByOrderByCreateTimeDesc();

	void saveAndFlush(Tag tag);

	void deleteById(String id);
}
