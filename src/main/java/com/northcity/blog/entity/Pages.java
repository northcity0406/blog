package com.northcity.blog.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Pages implements Serializable {
	private int id;
	private String type;
	private String md;
	private String html;

	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "type", nullable = false, length = 128)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Basic
	@Column(name = "md", nullable = true, length = -1)
	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	@Basic
	@Column(name = "html", nullable = true, length = -1)
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pages pages = (Pages) o;
		return id == pages.id &&
				Objects.equals(type, pages.type) &&
				Objects.equals(md, pages.md) &&
				Objects.equals(html, pages.html);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, md, html);
	}
}
