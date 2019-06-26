package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(TagPK.class)
public class Tag implements Serializable {
  private int aid;
  private String id;
  private String name;
  private Date createTime;
  private Date updateTime;
  private Boolean status;
  private Integer articleCount;

  @Id
  @Column(name = "aid", nullable = false)
  public int getAid() {
    return aid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  @Id
  @Column(name = "id", nullable = false, length = 128)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name", nullable = true, length = 255)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
  @Column(name = "update_time", nullable = true)
  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Basic
  @Column(name = "status", nullable = true)
  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Basic
  @Column(name = "article_count", nullable = true)
  public Integer getArticleCount() {
    return articleCount;
  }

  public void setArticleCount(Integer articleCount) {
    this.articleCount = articleCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag = (Tag) o;
    return aid == tag.aid
        && createTime == tag.createTime
        && Objects.equals(id, tag.id)
        && Objects.equals(name, tag.name)
        && Objects.equals(updateTime, tag.updateTime)
        && Objects.equals(status, tag.status)
        && Objects.equals(articleCount, tag.articleCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aid, id, name, createTime, updateTime, status, articleCount);
  }

  @Override
  public String toString() {
    return "Tag{" +
            "aid=" + aid +
            ", id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", status=" + status +
            ", articleCount=" + articleCount +
            '}';
  }
}
