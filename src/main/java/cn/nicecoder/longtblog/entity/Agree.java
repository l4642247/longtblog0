package cn.nicecoder.longtblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: longt
 * @Date: 2019/5/13 15:52
 * @Description:
 */
@Entity
public class Agree  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    private Long agreeid;

    private Long userid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pudate;

    private String ob1;

    private String ob2;

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

    public Long getAgreeid() {
        return agreeid;
    }

    public void setAgreeid(Long agreeid) {
        this.agreeid = agreeid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getPudate() {
        return pudate;
    }

    public void setPudate(Date pudate) {
        this.pudate = pudate;
    }

    public String getOb1() {
        return ob1;
    }

    public void setOb1(String ob1) {
        this.ob1 = ob1;
    }

    public String getOb2() {
        return ob2;
    }

    public void setOb2(String ob2) {
        this.ob2 = ob2;
    }

    public Agree() {
    }

    public Agree(String type, Long agreeid, Long userid, Date pudate) {
        this.type = type;
        this.agreeid = agreeid;
        this.userid = userid;
        this.pudate = pudate;
        this.ob1 = ob1;
        this.ob2 = ob2;
    }
}
