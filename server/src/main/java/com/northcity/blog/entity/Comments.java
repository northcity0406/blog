package com.northcity.blog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comments {
    private int id;
    private int articleId;
    private int parentId;
    private Integer replyId;
    private String name;
    private String email;
    private String content;
    private String sourceContent;
    private Timestamp createTime;
    private Timestamp deleteTime;
    private Byte status;
    private Integer isAuthor;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "articleID")
    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "parentID")
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "replyID")
    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "sourceContent")
    public String getSourceContent() {
        return sourceContent;
    }

    public void setSourceContent(String sourceContent) {
        this.sourceContent = sourceContent;
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
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "is_author")
    public Integer getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(Integer isAuthor) {
        this.isAuthor = isAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id &&
                articleId == comments.articleId &&
                parentId == comments.parentId &&
                Objects.equals(replyId, comments.replyId) &&
                Objects.equals(name, comments.name) &&
                Objects.equals(email, comments.email) &&
                Objects.equals(content, comments.content) &&
                Objects.equals(sourceContent, comments.sourceContent) &&
                Objects.equals(createTime, comments.createTime) &&
                Objects.equals(deleteTime, comments.deleteTime) &&
                Objects.equals(status, comments.status) &&
                Objects.equals(isAuthor, comments.isAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, articleId, parentId, replyId, name, email, content, sourceContent, createTime, deleteTime, status, isAuthor);
    }
}
