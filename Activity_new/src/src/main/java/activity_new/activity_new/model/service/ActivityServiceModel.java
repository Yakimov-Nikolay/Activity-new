package activity_new.activity_new.model.service;

import activity_new.activity_new.model.entity.PictureEntity;
import activity_new.activity_new.model.entity.UserEntity;

import java.util.HashSet;
import java.util.Set;

public class ActivityServiceModel extends BaseServiceModel{

    private String title;
    private String description;
    private UserEntity author;
    private String videoUrl;
    private Set<PictureEntity> pictures = new HashSet<>();

    public ActivityServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public ActivityServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ActivityServiceModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ActivityServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public ActivityServiceModel setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}

