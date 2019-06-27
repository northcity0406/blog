package com.northcity.blog.dao;

import com.northcity.blog.entity.Friends;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends
        PagingAndSortingRepository<Friends, Integer>,JpaRepository<Friends,Integer>{

  @Override
  List<Friends> findAll();

  Page<Friends> findAll(Pageable pageable);


  Friends findFriendsById(int friendid);

  @Override
  <S extends Friends> S saveAndFlush(S s);

  @Override
  void delete(Friends friends);

  void deleteById(int friendId);
}
