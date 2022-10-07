package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class News {

    private int id;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String title;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String content;

    private Date createdDate;

    private int views;

    private int author;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String authorName;

    private int categoryID;

    private int likes;

    private int dislikes;

    public News() {
    }

    public News(int id, String title, String content, Date createdDate, int views, int author, String authorName, int categoryID, int likes, int dislikes) {
        this();
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.views = views;
        this.author = author;
        this.authorName = authorName;
        this.categoryID = categoryID;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationTime() {
        return createdDate;
    }

    public void setCreationTime(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
