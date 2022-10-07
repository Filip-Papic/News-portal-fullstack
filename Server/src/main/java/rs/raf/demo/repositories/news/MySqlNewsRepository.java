package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.*;

public class MySqlNewsRepository extends MySqlAbstractRepository implements NewsRepository {

    @Override
    public List<News> allNews() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY createdDate DESC");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return news;
    }

    @Override
    public News findNews(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        News news = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                news = new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return news;
    }

    @Override
    public List<News> findByCategory(Integer catId, Integer pageId, Integer limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE categoryID = ? ORDER BY createdDate DESC LIMIT ?, ?");
            preparedStatement.setInt(1, catId);
            preparedStatement.setInt(2, (pageId - 1) * limit);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return news;
    }

    @Override
    public List<News> findByTag(Integer tagId, Integer pageId, Integer limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id IN (SELECT newsID FROM tags_for_news WHERE tagID = ?) ORDER BY createdDate DESC LIMIT ?, ?");
            preparedStatement.setInt(1, tagId);
            preparedStatement.setInt(2, (pageId - 1) * limit);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return news;
    }

    @Override
    public List<News> findNewsByPage(Integer page, Integer limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY createdDate DESC LIMIT ?, ?");
            preparedStatement.setInt(1, (page - 1) * limit);
            preparedStatement.setInt(2, limit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return news;
    }

    @Override
    public List<News> findPopular() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY views DESC LIMIT 10");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> findMostLiked() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY likes DESC LIMIT 10");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> findNew() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY createdDate DESC LIMIT 10");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> findSimilar(Integer newsID, Integer page, Integer limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Integer> tagIDs = new ArrayList<>();
        List<Integer> newsIDs = new ArrayList<>();
        List<News> news = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT tagID FROM tags_for_news WHERE newsID = ?");
            preparedStatement.setInt(1, newsID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tagIDs.add(resultSet.getInt("tagID"));
            }

            for(Integer i : tagIDs) {
                preparedStatement = connection.prepareStatement("SELECT DISTINCT newsID FROM tags_for_news WHERE tagID = ?");
                preparedStatement.setInt(1, i);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    newsIDs.add(resultSet.getInt("newsID"));
                }
            }

            Set<Integer> set = new LinkedHashSet<>(newsIDs);
            newsIDs.clear();
            newsIDs.addAll(set);

            for(Integer i : newsIDs) {
                preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id = ? LIMIT ?, ?");
                preparedStatement.setInt(1, i);
                preparedStatement.setInt(2, (page - 1) * limit);
                preparedStatement.setInt(3, limit);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    news.add(new News(resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("content"),
                            resultSet.getDate("createdDate"),
                            resultSet.getInt("views"),
                            resultSet.getInt("author"),
                            resultSet.getString("authorName"),
                            resultSet.getInt("categoryID"),
                            resultSet.getInt("likes"),
                            resultSet.getInt("dislikes")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> search(String query, Integer page, Integer limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<News> news = new ArrayList<>();

        try {
            connection = newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE title LIKE ? OR content LIKE ? LIMIT ?, ?");
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setInt(3, (page - 1) * limit);
            preparedStatement.setInt(4, limit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("createdDate"),
                        resultSet.getInt("views"),
                        resultSet.getInt("author"),
                        resultSet.getString("authorName"),
                        resultSet.getInt("categoryID"),
                        resultSet.getInt("likes"),
                        resultSet.getInt("dislikes")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return news;
    }

    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO news (title, content, author, authorName, categoryID, createdDate, views, likes, dislikes) VALUES (?, ?, ?, ?, ?, now(), 0, 0, 0)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getAuthor());
            preparedStatement.setString(4, news.getAuthorName());
            preparedStatement.setInt(5, news.getCategoryID());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                news.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public News editNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("UPDATE news SET title = ?, content = ?, author = ?, authorName = ?, views = ?, categoryID = ?, likes = ?, dislikes = ? WHERE id = ?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getAuthor());
            preparedStatement.setString(4, news.getAuthorName());
            preparedStatement.setInt(5, news.getViews());
            preparedStatement.setInt(6, news.getCategoryID());
            preparedStatement.setInt(7, news.getLikes());
            preparedStatement.setInt(8, news.getDislikes());
            preparedStatement.setInt(9, news.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return news;
    }

    @Override
    public void deleteNews(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM news WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }
}
