package com.northcity.blog.dao;

import com.northcity.blog.entity.FriendsType;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface FriendTypeRepository extends CrudRepository<FriendsType,Integer> {
	FriendsType getFriendsTypeById(int id);

	List<FriendsType> findAll();
}
