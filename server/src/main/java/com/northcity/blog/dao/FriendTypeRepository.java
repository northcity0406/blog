package com.northcity.blog.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
import com.northcity.blog.entity.FriendsType;

public interface FriendTypeRepository extends CrudRepository<FriendsType,Integer> {
	FriendsType getFriendsTypeById(int id);

	List<FriendsType> findAll();
}
