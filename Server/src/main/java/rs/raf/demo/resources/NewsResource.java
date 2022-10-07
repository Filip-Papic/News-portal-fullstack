package rs.raf.demo.resources;

import rs.raf.demo.entities.News;
import rs.raf.demo.services.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.newsService.allNews()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News find(@PathParam("id") Integer id) {
        return this.newsService.findNews(id);
    }

    @GET
    @Path("/page/{pageId}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findNewsByPage(@PathParam("pageId") Integer pageId, @PathParam("limit") Integer limit) {
        return this.newsService.findNewsByPage(pageId, limit);
    }

    @GET
    @Path("/category/{catId}/page/{pageId}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findByCategory(@PathParam("catId") Integer catId, @PathParam("pageId") Integer pageId, @PathParam("limit") Integer limit) {
        return this.newsService.findByCategory(catId, pageId, limit);
    }

    @GET
    @Path("/tag/{tagId}/page/{pageId}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findByTag(@PathParam("tagId") Integer tagId, @PathParam("pageId") Integer pageId, @PathParam("limit") Integer limit) {
        return this.newsService.findByTag(tagId, pageId, limit);
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findPopular() {
        return this.newsService.findPopular();
    }

    @GET
    @Path("/most-liked")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findMostLiked() {
        return this.newsService.findMostLiked();
    }

    @GET
    @Path("/similar/{id}/{page}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findSimilar(@PathParam("id") Integer id, @PathParam("page") Integer page, @PathParam("limit") Integer limit) {
        return this.newsService.findSimilar(id, page, limit);
    }

    @GET
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> findNew() {
        return this.newsService.findNew();
    }

    @GET
    @Path("/search/{query}/page/{pageId}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> search(@PathParam("query") String query, @PathParam("pageId") Integer pageId, @PathParam("limit") Integer limit) {
        return this.newsService.search(query, pageId, limit);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(@Valid News news) {
        return this.newsService.addNews(news);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News edit(@PathParam("id") Integer id, @Valid News news) {
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/like/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News like(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setLikes(news.getLikes() + 1);
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/unlike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News unlike(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setLikes(news.getLikes() - 1);
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/like-after-dislike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News likeAfterDislike(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setLikes(news.getLikes() + 1);
        news.setDislikes(news.getDislikes() - 1);
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/dislike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News dislike(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setDislikes(news.getDislikes() + 1);
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/undislike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News undislike(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setDislikes(news.getDislikes() - 1);
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/dislike-after-like/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News dislikeAfterLike(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setDislikes(news.getDislikes() + 1);
        news.setLikes(news.getLikes() - 1);
        return this.newsService.editNews(news);
    }

    @PUT
    @Path("/view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News view(@PathParam("id") Integer id) {
        News news = this.newsService.findNews(id);
        news.setViews(news.getViews() + 1);
        return this.newsService.editNews(news);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.newsService.deleteNews(id);
    }
}
