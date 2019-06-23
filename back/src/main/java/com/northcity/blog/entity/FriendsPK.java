package com.northcity.blog.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FriendsPK implements Serializable {
	private int aid;
	private String friendId;

	@Column(name = "aid", nullable = false)
	@Id
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Column(name = "friend_id", nullable = false, length = 128)
	@Id
	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FriendsPK friendsPK = (FriendsPK) o;
		return aid == friendsPK.aid &&
				Objects.equals(friendId, friendsPK.friendId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(aid, friendId);
	}
}
