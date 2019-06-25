package com.northcity.blog.service;

import com.northcity.blog.dao.ArticleRepository;
import com.northcity.blog.entity.Article;
import com.northcity.blog.service.interfaceDecla.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("ArticleService")
public class ArticleImpl implements ArticleService {

    /** 不能用static修飾 */
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }


    public Page<Article> findAllByStatus(Pageable pageable,Byte status){
        return articleRepository.findAllByStatus(pageable,status);
    }


    @Override
    public void saveAndFlush(Article article) {
        articleRepository.saveAndFlush(article);
    }

    @Override
    public Article findArticleById(String id) {
        return articleRepository.findArticleById(id);
    }
}
