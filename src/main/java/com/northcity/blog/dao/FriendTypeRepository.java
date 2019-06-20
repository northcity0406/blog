package com.northcity.blog.dao;

import com.northcity.blog.entity.FriendsType;
import org.springframework.data.repository.CrudRepository;

public interface FriendTypeRepository extends CrudRepository<FriendsType,Integer> {
	FriendsType getFriendsTypeById(int id);
}
