package rs.raf.demo.repositories.comment;

import rs.raf.demo.entities.Comment;

import java.util.List;

public interface CommentRepository {

    Comment addComment(Comment comment);
    List<Comment> allNewsComments(Integer id);
    Comment findComment(Integer id);
    Comment updateComment(Comment comment);
    void deleteComment(Integer id);
}
