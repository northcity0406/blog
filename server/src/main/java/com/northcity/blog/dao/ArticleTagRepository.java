package com.northcity.blog.dao;

import com.northcity.blog.entity.Articletags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ArticleTagRepository extends JpaRepository<Articletags,Integer> {

    List<Articletags> findAllByArticleId(int articleId);

    @Override
    <S extends Articletags> S saveAndFlush(S s);
}
