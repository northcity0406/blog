package com.northcity.blog.dao;

import com.northcity.blog.entity.Category;
import com.northcity.blog.entity.CategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryPK> {
	@Override
	List<Category> findAll();

	@Override
	<S extends Category> S saveAndFlush(S s);

	Category findCategoryById(String id);

	void deleteById(String id);
}
