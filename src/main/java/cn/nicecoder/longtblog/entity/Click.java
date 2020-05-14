package cn.nicecoder.longtblog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: longt
 * @Date: 2019/5/24 10:27
 * @Description:
 */
@Entity
public class Click implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "article_id",referencedColumnName = "id")
    @JsonBackReference
    private Article article;

    public Click(String type, Date createTime, User user, Article article) {
        this.type = type;
        this.createTime = createTime;
        this.user = user;
        this.article = article;
    }

    public Click() {
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
