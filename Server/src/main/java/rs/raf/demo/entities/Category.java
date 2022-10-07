package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Category {

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String categoryName;

    @NotNull(message = "This field is required")
    @NotEmpty(message = "This field is required")
    private String categoryDescription;

    private int id;

    public Category() {
    }

    public Category(int id, String categoryName, String categoryDescription) {
        this();
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
