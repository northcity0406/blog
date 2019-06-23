package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Friends;

import java.util.*;


public interface FriendsService {
	Friends findFriendsByAid(int aid);

	Friends findFriendsByFriendId(String friendid);

	List<Friends> findAll();

	void deleteByAid(int aid);

	void saveAndUpdate(Friends friends);

}
