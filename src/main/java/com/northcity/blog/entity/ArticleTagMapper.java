package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "article_tag_mapper", schema = "blog", catalog = "")
public class ArticleTagMapper {
  private int id;
  private String articleId;
  private String tagId;
  private Date createTime;

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
  @Column(name = "tag_id", nullable = false, length = 128)
  public String getTagId() {
    return tagId;
  }

  public void setTagId(String tagId) {
    this.tagId = tagId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArticleTagMapper that = (ArticleTagMapper) o;
    return id == that.id
        && createTime == that.createTime
        && Objects.equals(articleId, that.articleId)
        && Objects.equals(tagId, that.tagId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, articleId, tagId, createTime);
  }


}
