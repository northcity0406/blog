package com.northcity.blog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Admin {
    private int id;
    private String name;
    private String password;
    private String salt;
    private String accessToken;
    private Integer tokenExpiresIn;
    private int status;
    private Timestamp lastLoginTime;
    private Boolean admin;
    private Timestamp createTime;
    private String token;

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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Basic
    @Column(name = "token_expires_in")
    public Integer getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    public void setTokenExpiresIn(Integer tokenExpiresIn) {
        this.tokenExpiresIn = tokenExpiresIn;
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
    @Column(name = "last_login_time")
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "admin")
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin1 = (Admin) o;
        return id == admin1.id &&
                status == admin1.status &&
                Objects.equals(name, admin1.name) &&
                Objects.equals(password, admin1.password) &&
                Objects.equals(salt, admin1.salt) &&
                Objects.equals(accessToken, admin1.accessToken) &&
                Objects.equals(tokenExpiresIn, admin1.tokenExpiresIn) &&
                Objects.equals(lastLoginTime, admin1.lastLoginTime) &&
                Objects.equals(admin, admin1.admin) &&
                Objects.equals(createTime, admin1.createTime) &&
                Objects.equals(token, admin1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, salt, accessToken, tokenExpiresIn, status, lastLoginTime, admin, createTime, token);
    }
}
