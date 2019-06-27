package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Article;
import com.northcity.blog.entity.Articletags;

import java.util.List;

public interface ArticleTagService {
    List<Articletags> findAllByArticleId(int articleId);

    void saveAndFlush(Articletags articletags);
}
