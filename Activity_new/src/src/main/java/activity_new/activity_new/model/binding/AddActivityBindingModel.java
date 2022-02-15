package activity_new.activity_new.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddActivityBindingModel {

    private String title;
    private String description;
    private String videoUrl;

    public AddActivityBindingModel() {
    }

    @NotBlank(message = "Cannot be empty")
    @Size(min = 3, max = 20, message = "name have to be between 3 and 20")
    public String getTitle() {
        return title;
    }

    public AddActivityBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public AddActivityBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank
    public String getVideoUrl() {
        return videoUrl;
    }

    public AddActivityBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
