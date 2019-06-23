package com.northcity.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "blog_config", schema = "blog", catalog = "")
public class BlogConfig implements Serializable {
	private int id;
	private String blogName;
	private String avatar;
	private String sign;
	private String wxpayQrcode;
	private String alipayQrcode;
	private String github;
	private String viewPassword;
	private String salt;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "blog_name", nullable = true, length = 255)
	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	@Basic
	@Column(name = "avatar", nullable = true, length = -1)
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Basic
	@Column(name = "sign", nullable = true, length = -1)
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Basic
	@Column(name = "wxpay_qrcode", nullable = true, length = -1)
	public String getWxpayQrcode() {
		return wxpayQrcode;
	}

	public void setWxpayQrcode(String wxpayQrcode) {
		this.wxpayQrcode = wxpayQrcode;
	}

	@Basic
	@Column(name = "alipay_qrcode", nullable = true, length = -1)
	public String getAlipayQrcode() {
		return alipayQrcode;
	}

	public void setAlipayQrcode(String alipayQrcode) {
		this.alipayQrcode = alipayQrcode;
	}

	@Basic
	@Column(name = "github", nullable = true, length = -1)
	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	@Basic
	@Column(name = "view_password", nullable = true, length = 255)
	public String getViewPassword() {
		return viewPassword;
	}

	public void setViewPassword(String viewPassword) {
		this.viewPassword = viewPassword;
	}

	@Basic
	@Column(name = "salt", nullable = true, length = 255)
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BlogConfig that = (BlogConfig) o;
		return id == that.id &&
				Objects.equals(blogName, that.blogName) &&
				Objects.equals(avatar, that.avatar) &&
				Objects.equals(sign, that.sign) &&
				Objects.equals(wxpayQrcode, that.wxpayQrcode) &&
				Objects.equals(alipayQrcode, that.alipayQrcode) &&
				Objects.equals(github, that.github) &&
				Objects.equals(viewPassword, that.viewPassword) &&
				Objects.equals(salt, that.salt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, blogName, avatar, sign, wxpayQrcode, alipayQrcode, github, viewPassword, salt);
	}

	@Override
	public String toString() {
		return "BlogConfig{" +
				"id=" + id +
				", blogName='" + blogName + '\'' +
				", avatar='" + avatar + '\'' +
				", sign='" + sign + '\'' +
				", wxpayQrcode='" + wxpayQrcode + '\'' +
				", alipayQrcode='" + alipayQrcode + '\'' +
				", github='" + github + '\'' +
				", viewPassword='" + viewPassword + '\'' +
				", salt='" + salt + '\'' +
				'}';
	}
}
