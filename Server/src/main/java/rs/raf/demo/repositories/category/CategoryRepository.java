package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;

import java.util.List;

public interface CategoryRepository {
    Category addCategory(Category category);
    List<Category> allCategories();
    Category findCategory(Integer id);
    Category editCategory(Category category);
    boolean deleteCategory(Integer id);
}
