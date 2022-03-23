package activity_new.activity_new.model.service;


import java.time.LocalDateTime;

public class CommentServiceModel{

private Long activityId;
    private String message;
    private String author;
    private LocalDateTime created;

    public CommentServiceModel() {
    }

    public Long getActivityId() {
        return activityId;
    }

    public CommentServiceModel setActivityId(Long activityId) {
        this.activityId = activityId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentServiceModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}
