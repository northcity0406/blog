package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(ArticlePK.class)
public class Article implements Serializable {
  private int aid;
  private String id;
  private String title;
  private String categoryId;
  private Date createTime;
  private Date deleteTime;
  private Date updateTime;
  private Date publishTime;
  private Byte status;
  private String content;
  private String htmlContent;
  private String cover;
  private String subMessage;
  private Integer pageview;
  private Boolean isEncrypt;

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
  @Column(name = "title", nullable = true, length = 255)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Basic
  @Column(name = "category_id", nullable = true, length = 128)
  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
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
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "publish_time", nullable = true)
  public Date getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(Date publishTime) {
    this.publishTime = publishTime;
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
  @Column(name = "content", nullable = true, length = -1)
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Basic
  @Column(name = "html_content", nullable = true, length = -1)
  public String getHtmlContent() {
    return htmlContent;
  }

  public void setHtmlContent(String htmlContent) {
    this.htmlContent = htmlContent;
  }

  @Basic
  @Column(name = "cover", nullable = true, length = -1)
  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  @Basic
  @Column(name = "sub_message", nullable = true, length = -1)
  public String getSubMessage() {
    return subMessage;
  }

  public void setSubMessage(String subMessage) {
    this.subMessage = subMessage;
  }

  @Basic
  @Column(name = "pageview", nullable = true)
  public Integer getPageview() {
    return pageview;
  }

  public void setPageview(Integer pageview) {
    this.pageview = pageview;
  }

  @Basic
  @Column(name = "is_encrypt", nullable = true)
  public Boolean getEncrypt() {
    return isEncrypt;
  }

  public void setEncrypt(Boolean encrypt) {
    isEncrypt = encrypt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Article article = (Article) o;
    return aid == article.aid
        && createTime == article.createTime
        && Objects.equals(id, article.id)
        && Objects.equals(title, article.title)
        && Objects.equals(categoryId, article.categoryId)
        && Objects.equals(deleteTime, article.deleteTime)
        && Objects.equals(updateTime, article.updateTime)
        && Objects.equals(publishTime, article.publishTime)
        && Objects.equals(status, article.status)
        && Objects.equals(content, article.content)
        && Objects.equals(htmlContent, article.htmlContent)
        && Objects.equals(cover, article.cover)
        && Objects.equals(subMessage, article.subMessage)
        && Objects.equals(pageview, article.pageview)
        && Objects.equals(isEncrypt, article.isEncrypt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        aid,
        id,
        title,
        categoryId,
        createTime,
        deleteTime,
        updateTime,
        publishTime,
        status,
        content,
        htmlContent,
        cover,
        subMessage,
        pageview,
        isEncrypt);
  }
}
