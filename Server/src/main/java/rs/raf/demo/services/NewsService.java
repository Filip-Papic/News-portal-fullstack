package rs.raf.demo.services;

import rs.raf.demo.entities.News;
import rs.raf.demo.repositories.news.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {

    public NewsService() {
        System.out.println(this);
    }

    @Inject
    private NewsRepository newsRepository;

    public List<News> allNews() {
        return this.newsRepository.allNews();
    }

    public News addNews(News news) {
        return this.newsRepository.addNews(news);
    }

    public News findNews(Integer id) {
        return this.newsRepository.findNews(id);
    }

    public List<News> findByCategory(Integer catId, Integer pageId, Integer limit) {
        return this.newsRepository.findByCategory(catId, pageId, limit);
    }

    public List<News> findByTag(Integer tagId, Integer pageId, Integer limit) {
        return this.newsRepository.findByTag(tagId, pageId, limit);
    }

    public List<News> findPopular() {
        return this.newsRepository.findPopular();
    }

    public List<News> findMostLiked() {
        return this.newsRepository.findMostLiked();
    }

    public List<News> findNew() {
        return this.newsRepository.findNew();
    }

    public List<News> findSimilar(Integer id, Integer page, Integer limit) {
        return this.newsRepository.findSimilar(id, page, limit);
    }

    public List<News> findNewsByPage(Integer page, Integer limit) {
        return this.newsRepository.findNewsByPage(page, limit);
    }

    public List<News> search(String search, Integer page, Integer limit) {
        return this.newsRepository.search(search, page, limit);
    }

    public News editNews(News news) {
        return this.newsRepository.editNews(news);
    }

    public void deleteNews(Integer id) {
        this.newsRepository.deleteNews(id);
    }
}
