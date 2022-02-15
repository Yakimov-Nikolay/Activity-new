package activity_new.activity_new.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.DataFormatException;

@Entity
@Table(name = "activities")
public class ActivityEntity extends BaseEntity {

    private String title;
    private String description;
    private UserEntity author;
    private String videoUrl;
    private LocalDateTime created;
    private Set<PictureEntity> pictures = new HashSet<>();

    @OneToMany(mappedBy = "comments", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public ActivityEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    private List<CommentEntity> commentEntityList;

    public ActivityEntity() {
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

    public ActivityEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
}
