package rs.raf.demo.repositories.tag;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Tag;

import java.util.List;

public interface TagRepository {
    List<Tag> getTags();
    List<Tag> allTagsForNews(Integer id);
    Tag findTag(Integer id);
    Tag findTagByName(String name);
    List<Tag> popularTags();
    Tag addTag(Tag tag);
    void addTagToNews(Integer newsID, Integer tagID);
    void deleteTag(Integer id);
    void deleteTagsForNews(Integer newsId);
    void deleteTagForNews(Integer newsId, Integer tagId);
}
