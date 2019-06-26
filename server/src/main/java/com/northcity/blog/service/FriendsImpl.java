package com.northcity.blog.service;

import com.northcity.blog.dao.FriendsRepository;
import com.northcity.blog.entity.Friends;
import com.northcity.blog.service.interfaceDecla.FriendsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("FriendsService")
public class FriendsImpl implements FriendsService {
	Logger logger = LoggerFactory.getLogger(FriendsImpl.class);

	@Autowired
	private FriendsRepository friendsRepository;

	@Override
	public Friends findFriendsByAid(int aid) {
		logger.info("id :" + aid);
		return friendsRepository.findFriendsByAid(aid);
	}

	@Override
	public Friends findFriendsByFriendId(String friendid) {
		return friendsRepository.findFriendsByFriendId(friendid);
	}

	@Override
	public List<Friends> findAll() {
		return friendsRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteByAid(int aid) {
		friendsRepository.deleteByAid(aid);
	}

	@Override
	public void deleteByFriendId(Friends friends) {
		friendsRepository.delete(friends);
	}

	@Override
	public void saveAndUpdate(Friends friends) {
		friendsRepository.saveAndFlush(friends);
	}

	@Override
	public Page<Friends> findAll(Pageable pageable) {
		return friendsRepository.findAll(pageable);
	}

}
