package rs.raf.demo.resources;

import rs.raf.demo.entities.Comment;
import rs.raf.demo.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Path("/news/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@PathParam("id") Integer id) {
        return Response.ok(this.commentService.allNewsComments(id)).build();
    }

    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(this.commentService.findComment(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@Valid Comment comment) {
        return this.commentService.addComment(comment);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment update(@PathParam("id") Integer id, @Valid Comment comment) {
        return this.commentService.updateComment(comment);
    }

    @PUT
    @Path("/like/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment like(@PathParam("id") Integer id) {
        Comment comment = this.commentService.findComment(id);
        comment.setLikes(comment.getLikes() + 1);
        return this.commentService.updateComment(comment);
    }

    @PUT
    @Path("/unlike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment undoLike(@PathParam("id") Integer id) {
        Comment comment = this.commentService.findComment(id);
        comment.setLikes(comment.getLikes() - 1);
        return this.commentService.updateComment(comment);
    }

    @PUT
    @Path("/like-after-dislike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment likeAfterDislike(@PathParam("id") Integer id) {
        Comment comment = this.commentService.findComment(id);
        comment.setLikes(comment.getLikes() + 1);
        comment.setDislikes(comment.getDislikes() - 1);
        return this.commentService.updateComment(comment);
    }

    @PUT
    @Path("/dislike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment dislike(@PathParam("id") Integer id) {
        Comment comment = this.commentService.findComment(id);
        comment.setDislikes(comment.getDislikes() + 1);
        return this.commentService.updateComment(comment);
    }

    @PUT
    @Path("/undislike/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment undoDislike(@PathParam("id") Integer id) {
        Comment comment = this.commentService.findComment(id);
        comment.setDislikes(comment.getDislikes() - 1);
        return this.commentService.updateComment(comment);
    }

    @PUT
    @Path("/dislike-after-like/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Comment dislikeAfterLike(@PathParam("id") Integer id) {
        Comment comment = this.commentService.findComment(id);
        comment.setLikes(comment.getLikes() - 1);
        comment.setDislikes(comment.getDislikes() + 1);
        return this.commentService.updateComment(comment);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.commentService.deleteComment(id);
    }
}
