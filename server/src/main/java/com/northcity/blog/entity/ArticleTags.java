package com.northcity.blog.entity;
import java.util.*;

public class ArticleTags {
    private Article article;
    private List<String> tags;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
