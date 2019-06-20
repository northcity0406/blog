package com.northcity.blog.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CategoryPK implements Serializable {
	private int aid;
	private String id;

	@Column(name = "aid", nullable = false)
	@Id
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Column(name = "id", nullable = false, length = 128)
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryPK that = (CategoryPK) o;
		return aid == that.aid &&
				Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aid, id);
	}
}
