package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Comment {

    private Integer id;

    private Integer newsID;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String author;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String content;

    private Date createdDate;

    private int likes;

    private int dislikes;

    public Comment() {
    }

    public Comment(Integer id, Integer newsID, String author, String content, Date createdDate, int likes, int dislikes) {
        this();
        this.id = id;
        this.newsID = newsID;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
