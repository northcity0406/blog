package com.northcity.blog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Article {
    private int id;
    private int userId;
    private String title;
    private int categoryId;
    private Timestamp createTime;
    private Timestamp deleteTime;
    private Timestamp updateTime;
    private Timestamp publishTime;
    private int status;
    private String content;
    private String htmlContent;
    private String cover;
    private String subMessage;
    private int pageview;
    private Integer isEncrypt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "categoryID")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
    @Column(name = "deleteTime")
    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
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
    @Column(name = "publishTime")
    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "htmlContent")
    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "subMessage")
    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    @Basic
    @Column(name = "pageview")
    public int getPageview() {
        return pageview;
    }

    public void setPageview(int pageview) {
        this.pageview = pageview;
    }

    @Basic
    @Column(name = "isEncrypt")
    public Integer getIsEncrypt() {
        return isEncrypt;
    }

    public void setIsEncrypt(Integer isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                userId == article.userId &&
                categoryId == article.categoryId &&
                status == article.status &&
                pageview == article.pageview &&
                Objects.equals(title, article.title) &&
                Objects.equals(createTime, article.createTime) &&
                Objects.equals(deleteTime, article.deleteTime) &&
                Objects.equals(updateTime, article.updateTime) &&
                Objects.equals(publishTime, article.publishTime) &&
                Objects.equals(content, article.content) &&
                Objects.equals(htmlContent, article.htmlContent) &&
                Objects.equals(cover, article.cover) &&
                Objects.equals(subMessage, article.subMessage) &&
                Objects.equals(isEncrypt, article.isEncrypt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, title, categoryId, createTime, deleteTime, updateTime, publishTime, status, content, htmlContent, cover, subMessage, pageview, isEncrypt);
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", createTime=" + createTime +
                ", deleteTime=" + deleteTime +
                ", updateTime=" + updateTime +
                ", publishTime=" + publishTime +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", cover='" + cover + '\'' +
                ", subMessage='" + subMessage + '\'' +
                ", pageview=" + pageview +
                ", isEncrypt=" + isEncrypt +
                '}';
    }
}
