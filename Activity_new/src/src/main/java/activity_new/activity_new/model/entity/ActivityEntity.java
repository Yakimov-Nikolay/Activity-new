package activity_new.activity_new.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "activities")
public class ActivityEntity extends BaseEntity {

    private String title;
    private String description;
    private UserEntity author;
    private String videoUrl;
    private LocalDateTime created;
    private List<PictureEntity> pictures;
    private List<CommentEntity> commentEntityList;
    private Long likeVideoCounter;
    private Long dislikeVideoCounter;

    public ActivityEntity() {
    }

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<PictureEntity> getPictures() {
        return pictures;
    }

    public ActivityEntity setPictures(List<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public ActivityEntity setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
        return this;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public ActivityEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @Lob
    public String getDescription() {
        return description;
    }

    public ActivityEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public ActivityEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @Column
    public LocalDateTime getCreated() {
        return created;
    }

    public ActivityEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    @Column
    public String getVideoUrl() {
        return videoUrl;
    }

    @Column
    public Long getLikeVideoCounter() {
        return this.likeVideoCounter;
    }

    public ActivityEntity setLikeVideoCounter(Long likeVideoCounter) {
        this.likeVideoCounter = likeVideoCounter;
        return this;
    }

    @Column
    public Long getDislikeVideoCounter() {
        return dislikeVideoCounter;
    }

    public ActivityEntity setDislikeVideoCounter(Long dislikeVideoCounter) {
        this.dislikeVideoCounter = dislikeVideoCounter;
        return this;
    }

    public ActivityEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

}
