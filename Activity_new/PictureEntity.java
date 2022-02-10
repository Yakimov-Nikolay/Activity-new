package activity_new.activity_new.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    private String title;
    private String url;
    private UserEntity author;
    private String description;
    private String publicId;
    private List<CommentEntity> comments;

    public PictureEntity() {
    }

    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public PictureEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PictureEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    @OneToMany
    public List<CommentEntity> getComments() {
        return comments;
    }

    public PictureEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
