package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Comments;

import java.util.List;

public interface CommentsService {
	List<Comments> findAll();
	void deleteById(int id);
}
