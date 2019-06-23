package com.northcity.blog.service.interfaceDecla;

import java.util.*;
import com.northcity.blog.entity.Category;

public interface CategoryService {
	List<Category> findAll();

	Category findCategoryById(String id);


	Category saveAndFlush(Category category);

	void deleteById(String id);

}
