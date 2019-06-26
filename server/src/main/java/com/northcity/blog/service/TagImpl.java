package com.northcity.blog.service;

import com.northcity.blog.dao.TagRepository;
import com.northcity.blog.entity.Tag;
import com.northcity.blog.service.interfaceDecla.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TagsService")
public class TagImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;


	@Override
	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	@Override
	public Tag findTagById(String id) {
		return tagRepository.findTagById(id);
	}

	@Override
	public List<Tag> findAllByOrderByCreateTimeDesc() {
		return tagRepository.findAllByOrderByCreateTimeDesc();
	}

	@Override
	public void saveAndFlush(Tag tag) {
		tagRepository.saveAndFlush(tag);
	}

	@Override
	public void deleteById(String id) {
		tagRepository.deleteById(id);
	}


}
