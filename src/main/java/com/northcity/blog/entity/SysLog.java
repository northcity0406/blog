package com.northcity.blog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sys_log", schema = "blog", catalog = "")
public class SysLog {
  private int id;
  private Date time;
  private String content;
  private String ip;

  @Id
  @Column(name = "id", nullable = false)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time", nullable = false)
  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
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
  @Column(name = "ip", nullable = true, length = 30)
  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SysLog sysLog = (SysLog) o;
    return id == sysLog.id
        && time == sysLog.time
        && Objects.equals(content, sysLog.content)
        && Objects.equals(ip, sysLog.ip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, time, content, ip);
  }
}
