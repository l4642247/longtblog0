package cn.nicecoder.longtblog.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: longt
 * @Date: 2019/4/23 16:16
 * @Description:
 */
@Entity
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String des;

    /*文章拥有的标签
    @ManyToMany(mappedBy="tags")
    //只需要设置mappedBy="games"表明Game实体是关系被维护端就可以了
    //级联保存、级联删除等之类的属性在多对多关系中是不需要设置
    //不能说删了游戏,把玩家也删掉,玩家还可以玩其他的游戏
    private Set<Article> articles = new HashSet<Article>();*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Tag() {
    }

    public Tag(String name, String des) {
        this.name = name;
        this.des = des;
    }
}
