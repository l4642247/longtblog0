package cn.nicecoder.longtblog.pojo;

import cn.nicecoder.longtblog.entity.Comment;

import java.util.List;

/**
 * @Author: longt
 * @Date: 2019/5/15 16:07
 * @Description:
 */
public class CommentResult extends Comment {
    private String name;
    private String pic;
    private String toname;
    private String topic;
    private List<CommentResult> commentResults;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<CommentResult> getCommentResults() {
        return commentResults;
    }

    public void setCommentResults(List<CommentResult> commentResults) {
        this.commentResults = commentResults;
    }
}
