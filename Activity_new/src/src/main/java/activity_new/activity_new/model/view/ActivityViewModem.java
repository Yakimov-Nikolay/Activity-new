package activity_new.activity_new.model.view;

import activity_new.activity_new.model.entity.UserEntity;

import java.time.LocalDateTime;

public class ActivityViewModem {


    private Long id;
    private String title;
    private String description;
    private UserEntity author;
    private String videoUrl;
    private LocalDateTime created;

    public ActivityViewModem() {
    }

    public Long getId() {
        return id;
    }

    public ActivityViewModem setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ActivityViewModem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityViewModem setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public ActivityViewModem setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ActivityViewModem setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public ActivityViewModem setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}
