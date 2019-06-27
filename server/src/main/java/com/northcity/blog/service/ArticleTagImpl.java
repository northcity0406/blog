package com.northcity.blog.service;

import com.northcity.blog.dao.ArticleTagRepository;
import com.northcity.blog.entity.Article;
import com.northcity.blog.entity.Articletags;
import com.northcity.blog.service.interfaceDecla.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArticleTagService")
public class ArticleTagImpl  implements ArticleTagService {

    @Autowired
    private ArticleTagRepository articleTagRepository;

    @Override
    public List<Articletags> findAllByArticleId(int articleId) {
        return articleTagRepository.findAllByArticleId(articleId);
    }

    @Override
    public void saveAndFlush(Articletags articletags) {
        articleTagRepository.saveAndFlush(articletags);
    }
}
