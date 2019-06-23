package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Comments implements Serializable {
  private int id;
  private String articleId;
  private int parentId;
  private Integer replyId;
  private String name;
  private String email;
  private String content;
  private String sourceContent;
  private Date createTime;
  private Date deleteTime;
  private Byte status;
  private Boolean isAuthor;

  @Id
  @Column(name = "id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "article_id", nullable = false, length = 128)
  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  @Basic
  @Column(name = "parent_id", nullable = false)
  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  @Basic
  @Column(name = "reply_id", nullable = true)
  public Integer getReplyId() {
    return replyId;
  }

  public void setReplyId(Integer replyId) {
    this.replyId = replyId;
  }

  @Basic
  @Column(name = "name", nullable = false, length = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "email", nullable = true, length = 128)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "content", nullable = false, length = -1)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Basic
  @Column(name = "source_content", nullable = true, length = -1)
  public String getSourceContent() {
    return sourceContent;
  }

  public void setSourceContent(String sourceContent) {
    this.sourceContent = sourceContent;
  }

  @Basic
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time", nullable = false)
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Basic
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "delete_time", nullable = true)
  public Date getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(Date deleteTime) {
    this.deleteTime = deleteTime;
  }

  @Basic
  @Column(name = "status", nullable = true)
  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  @Basic
  @Column(name = "is_author", nullable = true)
  public Boolean getAuthor() {
    return isAuthor;
  }

  public void setAuthor(Boolean author) {
    isAuthor = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comments comments = (Comments) o;
    return id == comments.id
        && parentId == comments.parentId
        && createTime == comments.createTime
        && Objects.equals(articleId, comments.articleId)
        && Objects.equals(replyId, comments.replyId)
        && Objects.equals(name, comments.name)
        && Objects.equals(email, comments.email)
        && Objects.equals(content, comments.content)
        && Objects.equals(sourceContent, comments.sourceContent)
        && Objects.equals(deleteTime, comments.deleteTime)
        && Objects.equals(status, comments.status)
        && Objects.equals(isAuthor, comments.isAuthor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        articleId,
        parentId,
        replyId,
        name,
        email,
        content,
        sourceContent,
        createTime,
        deleteTime,
        status,
        isAuthor);
  }
}
