package com.northcity.blog.dao;

import com.northcity.blog.entity.Admin;
import com.northcity.blog.entity.Friends;
import com.northcity.blog.entity.FriendsPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends
        PagingAndSortingRepository<Friends, FriendsPK>,
        JpaRepository<Friends,FriendsPK> {

  @Override
  List<Friends> findAll();

  Page<Friends> findAll(Pageable pageable);


  Friends findFriendsByAid(int aid);

  Friends findFriendsByFriendId(String friendid);

  @Override
  <S extends Friends> S saveAndFlush(S s);

  @Override
  void delete(Friends friends);

  void deleteByAid(int aid);
}
