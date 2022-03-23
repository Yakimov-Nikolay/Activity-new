package activity_new.activity_new.model.service;

import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.CommentEntity;
import activity_new.activity_new.model.entity.UserEntity;

import java.util.List;

public class PictureServiceModel extends BaseServiceModel {

    private String title;
    private String url;
    private UserEntity author;
    private String description;
    private String publicId;
    private ActivityEntity activity;
    private List<CommentEntity> comments;

    public String getTitle() {
        return title;
    }

    public PictureServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureServiceModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PictureServiceModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PictureServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureServiceModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public ActivityEntity getActivity() {
        return activity;
    }

    public PictureServiceModel setActivity(ActivityEntity activity) {
        this.activity = activity;
        return this;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public PictureServiceModel setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
