package rs.raf.demo.repositories.category;

import rs.raf.demo.entities.Category;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCategoryRepository extends MySqlAbstractRepository implements CategoryRepository {
    @Override
    public Category addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE name = ?", generatedColumns);
            preparedStatement.setString(1, category.getCategoryName());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Category with that name already exists!");
                closeStatement(preparedStatement);
                closeResultSet(resultSet);
                closeConnection(connection);
                return null;
            }

            preparedStatement = connection.prepareStatement("INSERT INTO categories (name, description) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryDescription());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    @Override
    public List<Category> allCategories() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Category> categories = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"),
                                            resultSet.getString("name"),
                                            resultSet.getString("description")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return categories;
    }

    @Override
    public Category findCategory(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Category category = null;

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                category = new Category(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("description"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return category;
    }

    @Override
    public Category editCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE id = ?");
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryDescription());
            preparedStatement.setInt(3, category.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }

        return category;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE categoryID = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Cant delete, Category has news. Delete its news first.");
                closeStatement(preparedStatement);
                closeResultSet(resultSet);
                closeConnection(connection);
                return false;
            }

            preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return true;
    }
}
