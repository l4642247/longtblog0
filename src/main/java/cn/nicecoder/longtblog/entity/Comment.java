package cn.nicecoder.longtblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: longt
 * @Date: 2019/5/13 15:39
 * @Description:
 */
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private Long discussid;

    private Long userid;

    private Long touserid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private int agree;

    private String status;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content", columnDefinition="BLOB", nullable=true)
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDiscussid() {
        return discussid;
    }

    public void setDiscussid(Long discussid) {
        this.discussid = discussid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getTouserid() {
        return touserid;
    }

    public void setTouserid(Long touserid) {
        this.touserid = touserid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return new String(content);
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Comment() {
    }

    public Comment(String type, Long discussid, Long userid, Long touserid, Date createTime, int agree, String status, byte[] content) {
        this.type = type;
        this.discussid = discussid;
        this.userid = userid;
        this.touserid = touserid;
        this.createTime = createTime;
        this.agree = agree;
        this.status = status;
        this.content = content;
    }
}


