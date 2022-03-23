package activity_new.activity_new.model.view;

public class ActivityViewModel {

    private Long id;
    private String title;
    private String description;
    private String videoUrl;


    public ActivityViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ActivityViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ActivityViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActivityViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ActivityViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

}
