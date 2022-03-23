package activity_new.activity_new.model.view;

public class PictureViewModel {

    private Long id;
    private String title;
    private String publicId;
    private String description;
    private String url;

    public PictureViewModel() {
    }

    public Long getId() {
        return id;
    }

    public PictureViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureViewModel setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PictureViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureViewModel setUrl(String url) {
        this.url = url;
        return this;
    }
}
