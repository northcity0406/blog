package com.northcity.blog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Category {
    private int id;
    private String name;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer status;
    private Integer articleCount;
    private Integer canDel;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "articleCount")
    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    @Basic
    @Column(name = "canDel")
    public Integer getCanDel() {
        return canDel;
    }

    public void setCanDel(Integer canDel) {
        this.canDel = canDel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(name, category.name) &&
                Objects.equals(createTime, category.createTime) &&
                Objects.equals(updateTime, category.updateTime) &&
                Objects.equals(status, category.status) &&
                Objects.equals(articleCount, category.articleCount) &&
                Objects.equals(canDel, category.canDel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createTime, updateTime, status, articleCount, canDel);
    }
}
