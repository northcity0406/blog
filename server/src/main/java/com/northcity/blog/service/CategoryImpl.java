package com.northcity.blog.service;

import com.northcity.blog.dao.CategoryRepository;
import com.northcity.blog.entity.Category;
import com.northcity.blog.service.interfaceDecla.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CategoryService")
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findCategoryById(String id) {
		return categoryRepository.findCategoryById(id);
	}

	@Override
	public Category saveAndFlush(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	@Override
	public void deleteById(String id) {
		categoryRepository.deleteById(id);
	}
}
