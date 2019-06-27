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
	public Tag findTagById(int id) {
		return tagRepository.findById(id);
	}
	@Override
	public void saveAndFlush(Tag tag) {
		tagRepository.saveAndFlush(tag);
	}

	@Override
	public void deleteById(int id) {
		tagRepository.deleteById(id);
	}


}
