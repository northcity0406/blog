package com.northcity.blog.service;

import com.northcity.blog.entity.Comments;
import com.northcity.blog.service.interfaceDecla.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentsService")
public class CommentsImple implements CommentsService {

	@Autowired
	private CommentsService commentsService;

	@Override
	public List<Comments> findAll() {
		return commentsService.findAll();
	}

	@Override
	public void deleteById(int id) {
		commentsService.deleteById(id);
	}
}
