package com.northcity.blog.service;

import com.northcity.blog.dao.FriendTypeRepository;
import com.northcity.blog.entity.FriendsType;
import com.northcity.blog.service.interfaceDecla.FriendTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FriendTypeService")
public class FriendTypeImpl implements FriendTypeService {

	@Autowired
	private FriendTypeRepository friendTypeRepository;

	@Override
	public FriendsType getFriendsTypeById(int id) {
		return friendTypeRepository.getFriendsTypeById(id);
	}
}
