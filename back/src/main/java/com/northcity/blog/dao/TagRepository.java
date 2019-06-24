package com.northcity.blog.dao;

import com.northcity.blog.entity.Tag;
import com.northcity.blog.entity.TagPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TagRepository extends JpaRepository<Tag, TagPK> {
	@Override
	List<Tag> findAll();

	Tag findTagById(String id);

	List<Tag> findAllByOrderByCreateTimeDesc();

	@Override
	<S extends Tag> S saveAndFlush(S s);

	void deleteById(String id);
}
