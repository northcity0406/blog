package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.FriendsType;

import java.util.*;

public interface FriendTypeService {
	FriendsType getFriendsTypeById(int id);

	List<FriendsType> findAll();

}
