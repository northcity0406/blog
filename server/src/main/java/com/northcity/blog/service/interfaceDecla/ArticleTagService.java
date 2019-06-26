package com.northcity.blog.service.interfaceDecla;

import com.northcity.blog.entity.Article;
import com.northcity.blog.entity.ArticleTagMapper;

import java.util.List;

public interface ArticleTagService {

    List<ArticleTagMapper> findAllByArticleId(String articleId);

    void saveAndFlush(ArticleTagMapper articleTagMapper);


}
