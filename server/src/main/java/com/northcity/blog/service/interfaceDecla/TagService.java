package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Tag;

import java.util.*;

public interface TagService {
	List<Tag> findAll();

	Tag findTagById(int id);

	void saveAndFlush(Tag tag);

	void deleteById(int id);
}
