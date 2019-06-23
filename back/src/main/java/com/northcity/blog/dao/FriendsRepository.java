package com.northcity.blog.dao;

import com.northcity.blog.entity.Friends;
import com.northcity.blog.entity.FriendsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, FriendsPK> {

  @Override
  List<Friends> findAll();

  Friends findFriendsByAid(int aid);

  Friends findFriendsByFriendId(String friendid);

  @Override
  <S extends Friends> S saveAndFlush(S s);

  @Override
  void delete(Friends friends);

  @Override
  void deleteById(FriendsPK friendsPK);

  void deleteByAid(int aid);
}
