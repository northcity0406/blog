package com.northcity.blog.dao;

import com.northcity.blog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer>, JpaRepository<Article,Integer> {

    @Override
    List<Article> findAll();

    @Override
    <S extends Article> S saveAndFlush(S s);


    Article findArticleById(int id);

    Page<Article> findAllByStatus(Pageable pageable,int status);

}
