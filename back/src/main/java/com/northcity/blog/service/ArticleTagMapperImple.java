package com.northcity.blog.service;

import com.northcity.blog.dao.ArticleTagMapperRepository;
import com.northcity.blog.entity.ArticleTagMapper;
import com.northcity.blog.service.interfaceDecla.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ArticleTagService")
public class ArticleTagMapperImple implements ArticleTagService {

    @Autowired
    private ArticleTagMapperRepository articleTagMapperRepository;

    @Override
    public List<ArticleTagMapper> findAllByArticleId(String articleId) {
        return articleTagMapperRepository.findAllByArticleId(articleId);
    }

    @Override
    public void saveAndFlush(ArticleTagMapper articleTagMapper) {
        articleTagMapperRepository.saveAndFlush(articleTagMapper);
    }
}
