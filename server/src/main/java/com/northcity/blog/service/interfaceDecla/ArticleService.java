package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();

    Page<Article> findAllByStatus(Pageable pageable, int status);

    Article saveAndFlush(Article article);

    Article findArticleById(int id);


}
