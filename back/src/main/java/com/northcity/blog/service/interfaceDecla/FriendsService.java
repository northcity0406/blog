package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Friends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.*;


public interface FriendsService {
	Friends findFriendsByAid(int aid);

	Friends findFriendsByFriendId(String friendid);

	List<Friends> findAll();

	void deleteByAid(int aid);

	void deleteByFriendId(Friends friends);


	void saveAndUpdate(Friends friends);

	Page<Friends> findAll(Pageable pageable);

}
