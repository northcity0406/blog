package com.northcity.blog.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AdminPK implements Serializable {
	private int aid;
	private String userId;

	@Column(name = "aid", nullable = false)
	@Id
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Column(name = "user_id", nullable = false, length = 128)
	@Id
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AdminPK adminPK = (AdminPK) o;
		return aid == adminPK.aid &&
				Objects.equals(userId, adminPK.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aid, userId);
	}
}
