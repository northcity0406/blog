package com.northcity.blog.dao;

import com.northcity.blog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {

	@Override
	List<Comments> findAll();

	@Override
	void deleteById(Integer integer);
}
