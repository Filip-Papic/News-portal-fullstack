package rs.raf.demo.repositories.tag;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlTagRepository extends MySqlAbstractRepository implements TagRepository {

    @Override
    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags ORDER BY name ASC LIMIT 50");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"),resultSet.getString("name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public List<Tag> allTagsForNews(Integer id) {
        List<Integer> tagIDs = new ArrayList<>();
        List<Tag> tagNames = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT tagID FROM tags_for_news WHERE newsID = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tagIDs.add(resultSet.getInt("tagID"));
            }

            for(Integer i : tagIDs) {
                preparedStatement = connection.prepareStatement("SELECT name FROM tags WHERE id = ?");
                preparedStatement.setInt(1, i);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    tagNames.add(new Tag(i, resultSet.getString("name")));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tagNames;
    }

    @Override
    public Tag findTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Tag tag = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tag = new Tag(resultSet.getInt("id"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public Tag findTagByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Tag tag = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tag = new Tag(resultSet.getInt("id"), resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public List<Tag> popularTags() {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT tagID, COUNT(tagID) AS count FROM tags_for_news GROUP BY tagID ORDER BY count DESC LIMIT 10");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("tagID"), findTag(resultSet.getInt("tagID")).getName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tags;
    }

    @Override
    public Tag addTag(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String[] generatedColumns = {"id"};
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags WHERE name = ?");
            preparedStatement.setString(1, tag.getName());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { //vrati istu ako vec ima
                tag.setId(resultSet.getInt("id"));
                return tag;
            }

            preparedStatement = connection.prepareStatement("INSERT INTO tags (name) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                tag.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;
    }

    @Override
    public void addTagToNews(Integer newsID, Integer tagID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("SELECT * FROM tags_for_news WHERE tagID = ? AND newsID = ?");
            preparedStatement.setInt(1, tagID);
            preparedStatement.setInt(2, newsID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return;
            }

            preparedStatement = connection.prepareStatement("INSERT INTO tags_for_news (tagID, newsID) VALUES(?,?)", generatedColumns);
            preparedStatement.setInt(1, tagID);
            preparedStatement.setInt(2, newsID);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deleteTag(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tags WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deleteTagsForNews(Integer newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tags_for_news WHERE newsID = ?");
            preparedStatement.setInt(1, newsId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM tags WHERE id NOT IN (SELECT tagID FROM tags_for_news)");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deleteTagForNews(Integer newsId, Integer tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tags_for_news WHERE newsID = ? AND tagID = ?");
            preparedStatement.setInt(1, newsId);
            preparedStatement.setInt(2, tagId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM tags WHERE id NOT IN (SELECT tagID FROM tags_for_news)");
            preparedStatement.executeUpdate();

            System.out.println("deleteTagForNews " + newsId + " " + tagId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }
}
