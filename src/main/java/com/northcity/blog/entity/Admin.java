package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(AdminPK.class)
public class Admin {
  private int aid;
  private String userId;
  private String username;
  private String password;
  private String salt;
  private String accessToken;
  private Integer tokenExpiresIn;
  private Date createTime;
  private boolean status;
  private Date lastLoginTime;

  @Id
  @Column(name = "aid", nullable = false)
  public int getAid() {
    return aid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  @Id
  @Column(name = "user_id", nullable = false, length = 128)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Basic
  @Column(name = "username", nullable = false, length = 255)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Basic
  @Column(name = "password", nullable = false, length = 255)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "salt", nullable = false, length = 255)
  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Basic
  @Column(name = "access_token", nullable = true, length = 255)
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Basic
  @Column(name = "token_expires_in", nullable = true)
  public Integer getTokenExpiresIn() {
    return tokenExpiresIn;
  }

  public void setTokenExpiresIn(Integer tokenExpiresIn) {
    this.tokenExpiresIn = tokenExpiresIn;
  }

  @Basic
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "create_time", nullable = true)
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Basic
  @Column(name = "status", nullable = false)
  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Basic
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_login_time", nullable = true)
  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Admin admin = (Admin) o;
    return aid == admin.aid
        && status == admin.status
        && Objects.equals(userId, admin.userId)
        && Objects.equals(username, admin.username)
        && Objects.equals(password, admin.password)
        && Objects.equals(salt, admin.salt)
        && Objects.equals(accessToken, admin.accessToken)
        && Objects.equals(tokenExpiresIn, admin.tokenExpiresIn)
        && Objects.equals(createTime, admin.createTime)
        && Objects.equals(lastLoginTime, admin.lastLoginTime);
  }

	@Override
  public int hashCode() {
    return Objects.hash(
        aid,
        userId,
        username,
        password,
        salt,
        accessToken,
        tokenExpiresIn,
        createTime,
        status,
        lastLoginTime);
  }

	@Override
	public String toString() {
		return "Admin{" +
				"aid=" + aid +
				", userId='" + userId + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", accessToken='" + accessToken + '\'' +
				", tokenExpiresIn=" + tokenExpiresIn +
				", createTime=" + createTime +
				", status=" + status +
				", lastLoginTime=" + lastLoginTime +
				'}';
	}

}
