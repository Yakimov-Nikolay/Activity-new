package activity_new.activity_new.model.view;

import activity_new.activity_new.model.entity.PictureEntity;
import activity_new.activity_new.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.*;

public class ActivityFullDetailsViewModel {

    private Long id;
    private String title;
    private String description;
    private String videoUrl;
    private String url;
    private UserEntity author;
    private LocalDateTime created;
    private List<PictureEntity> pictures;
    private boolean canDelete;

    public ActivityFullDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ActivityFullDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ActivityFullDetailsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ActivityFullDetailsViewModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityFullDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ActivityFullDetailsViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ActivityFullDetailsViewModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ActivityFullDetailsViewModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ActivityFullDetailsViewModel setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ActivityFullDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

}
