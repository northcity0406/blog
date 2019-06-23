package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(CategoryPK.class)
public class Category implements Serializable {
  private int aid;
  private String id;
  private String name;
  private Date createTime;
  private Date updateTime;
  private Boolean status;
  private Integer articleCount;
  private boolean canDel;

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

  @Basic
  @Column(name = "can_del", nullable = false)
  public boolean isCanDel() {
    return canDel;
  }

  public void setCanDel(boolean canDel) {
    this.canDel = canDel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return aid == category.aid
        && createTime == category.createTime
        && canDel == category.canDel
        && Objects.equals(id, category.id)
        && Objects.equals(name, category.name)
        && Objects.equals(updateTime, category.updateTime)
        && Objects.equals(status, category.status)
        && Objects.equals(articleCount, category.articleCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aid, id, name, createTime, updateTime, status, articleCount, canDel);
  }

  @Override
  public String toString() {
    return "Category{" +
            "aid=" + aid +
            ", id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", status=" + status +
            ", articleCount=" + articleCount +
            ", canDel=" + canDel +
            '}';
  }
}
