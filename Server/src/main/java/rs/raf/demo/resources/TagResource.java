package rs.raf.demo.resources;

import rs.raf.demo.entities.Tag;
import rs.raf.demo.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tags")
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTags() {
        return Response.ok(this.tagService.getTags()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@PathParam("id") Integer id) {
        return this.tagService.findTag(id);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag findByName(@PathParam("name") String name) {
        return this.tagService.findTagByName(name);
    }

    @GET
    @Path("/tags-for-news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTagsForNews(@PathParam("id") Integer id) {
        return Response.ok(this.tagService.allTagsForNews(id)).build();
    }

    @GET
    @Path("/popular")
    @Produces(MediaType.APPLICATION_JSON)
    public Response popularTags() {
        return Response.ok(this.tagService.popularTags()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(@Valid Tag tag) {
        return this.tagService.addTag(tag);
    }

    @POST
    @Path("/tags-for-news/{newsId}/{tagId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void addTagToNews(@PathParam("newsId") Integer newsId, @PathParam("tagId") Integer tagId) {
        this.tagService.addTagToNews(newsId, tagId);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.tagService.deleteTag(id);
    }

    @DELETE
    @Path("/tags-for-news/{newsId}")
    public void deleteTagsForNews(@PathParam("newsId") Integer newsId) {
        this.tagService.deleteTagsForNews(newsId);
    }

    @DELETE
    @Path("/tags-for-news/{newsId}/{tagId}")
    public void deleteTagForNews(@PathParam("newsId") Integer newsId, @PathParam("tagId") Integer tagId) {
        this.tagService.deleteTagForNews(newsId, tagId);
    }
}
