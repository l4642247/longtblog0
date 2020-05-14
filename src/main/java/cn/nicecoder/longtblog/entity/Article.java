package cn.nicecoder.longtblog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: longt
 * @Date: 2019/4/1 14:40
 * @Description:
 */
@Entity
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *     @Id
     *     @Column(name = "ID")
     *     @GenericGenerator(name = "idGenerator", strategy = "uuid")
     *     @GeneratedValue(generator = "idGenerator") //可在ID上面加上ID生成策略
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String author;
    private String summary;
    private String cover;

    private int agree;
    private String type;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="content", columnDefinition="BLOB", nullable=true)
    private byte[] content;
    private Long   click;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "catalog_id")
    @JsonBackReference
    private Catalog catalog;

    //关系维护端，负责多对多关系的绑定和解除
    //@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(Article)
    //inverseJoinColumns指定外键的名字，要关联的关系被维护端(tag)
    //其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为article_tag
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即article_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即tag_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="article_tag",joinColumns=@JoinColumn(name="article_id"),
            inverseJoinColumns=@JoinColumn(name="tag_id"))
    @JsonBackReference
    private Set<Tag> tags = new HashSet<Tag>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return new String(content);
    }

    public Article() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public Article(String title, String summary, String author, byte[] content, Long click, String status, Date createTime, Date updateTime, int agree, String type) {
        this.summary = summary;
        this.title = title;
        this.author = author;
        this.content = content;
        this.click = click;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.agree = agree;
        this.type = type;
    }
}
