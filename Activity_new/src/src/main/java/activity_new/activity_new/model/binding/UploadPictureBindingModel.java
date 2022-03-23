package activity_new.activity_new.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class UploadPictureBindingModel {

    private String title;
    private MultipartFile picture;
    private String description;

    public String getTitle() {
        return title;
    }

    public UploadPictureBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public UploadPictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UploadPictureBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
