package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Category;

import java.util.*;

public interface CategoryService {
	List<Category> findAll();

	Category findCategoryById(int id);

	Category saveAndFlush(Category category);

	void deleteById(int id);

}
