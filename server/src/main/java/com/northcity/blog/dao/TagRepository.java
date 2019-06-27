package com.northcity.blog.dao;

import com.northcity.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
	@Override
	List<Tag> findAll();

	Tag findById(int id);

	@Override
	<S extends Tag> S saveAndFlush(S s);

	void deleteById(int id);
}
