package activity_new.activity_new.model.entity;

import org.thymeleaf.standard.expression.Each;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    private String title;
    private String url;
    private UserEntity author;
    private String description;
    private String publicId;
    private ActivityEntity activity;


    public PictureEntity() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public ActivityEntity getActivity() {
        return activity;
    }

    public PictureEntity setActivity(ActivityEntity activity) {
        this.activity = activity;
        return this;
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(nullable = false)
    public String getPublicId() {
        return publicId;
    }

    public PictureEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

}
