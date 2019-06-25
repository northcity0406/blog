package com.northcity.blog.dao;

import com.northcity.blog.entity.ArticleTagMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ArticleTagMapperRepository extends JpaRepository<ArticleTagMapper,Integer> {

    List<ArticleTagMapper> findAllByArticleId(String articleId);


    @Override
    <S extends ArticleTagMapper> S saveAndFlush(S s);
}
