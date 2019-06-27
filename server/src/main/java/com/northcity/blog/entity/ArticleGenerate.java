package com.northcity.blog.entity;
import java.util.*;

public class ArticleGenerate extends Article{
    private String categoryName;
    private List<Integer> tags;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}
