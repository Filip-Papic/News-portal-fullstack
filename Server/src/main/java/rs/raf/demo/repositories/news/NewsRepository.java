package rs.raf.demo.repositories.news;

import rs.raf.demo.entities.News;

import java.util.List;

public interface NewsRepository {
    List<News> allNews();
    News findNews(Integer id);
    List<News> findByCategory(Integer catId, Integer pageId, Integer limit);
    List<News> findByTag(Integer tagId, Integer pageId, Integer limit);
    List<News> findNewsByPage(Integer page, Integer limit);
    List<News> findPopular();
    List<News> findMostLiked();
    List<News> findNew();
    List<News> findSimilar(Integer id, Integer page, Integer limit);
    List<News> search(String query, Integer page, Integer limit);
    News addNews(News news);
    News editNews(News news);
    void deleteNews(Integer id);
}
