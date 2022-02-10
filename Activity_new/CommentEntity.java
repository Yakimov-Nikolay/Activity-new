package activity_new.activity_new.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private UserEntity author;
    private Boolean approved;
    private LocalDateTime created;
    private ActivityEntity activity;
    private String content;

    public CommentEntity() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
    @Column(nullable = false)
    public Boolean getApproved() {
        return approved;
    }

    public CommentEntity setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }
    @Column(nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public CommentEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public ActivityEntity getActivity() {
        return activity;
    }

    public CommentEntity setActivity(ActivityEntity activity) {
        this.activity = activity;
        return this;
    }

    @Lob
    public String getContent() {
        return content;
    }

    public CommentEntity setContent(String content) {
        this.content = content;
        return this;
    }
}
