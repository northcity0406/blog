package com.northcity.blog.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends_type", schema = "blog", catalog = "")
public class FriendsType {
	private int id;
	private String name;
	private Integer count;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	@Column(name = "count", nullable = true)
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FriendsType that = (FriendsType) o;
		return id == that.id &&
				Objects.equals(name, that.name) &&
				Objects.equals(count, that.count);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, count);
	}
}
