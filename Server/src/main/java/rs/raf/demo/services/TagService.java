package rs.raf.demo.services;

import rs.raf.demo.entities.News;
import rs.raf.demo.entities.Tag;
import rs.raf.demo.repositories.tag.TagRepository;

import javax.inject.Inject;
import java.util.List;

public class TagService {

    public TagService() {
        System.out.println(this);
    }

    @Inject
    private TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return this.tagRepository.addTag(tag);
    }

    public List<Tag> getTags() {
        return this.tagRepository.getTags();
    }

    public List<Tag> allTagsForNews(Integer id){
        return this.tagRepository.allTagsForNews(id);
    }

    public Tag findTag(Integer id) {
        return this.tagRepository.findTag(id);
    }

    public Tag findTagByName(String name) {
        return this.tagRepository.findTagByName(name);
    }

    public List<Tag> popularTags() {
        return this.tagRepository.popularTags();
    }

    public void addTagToNews(Integer newsId, Integer tagId) {
        this.tagRepository.addTagToNews(newsId, tagId);
    }

    public void deleteTag(Integer id) {
        this.tagRepository.deleteTag(id);
    }

    public void deleteTagsForNews(Integer newsId) {
        this.tagRepository.deleteTagsForNews(newsId);
    }

    public void deleteTagForNews(Integer newsId, Integer tagId) {
        this.tagRepository.deleteTagForNews(newsId, tagId);
    }
}
