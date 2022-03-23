package activity_new.activity_new.model.binding;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddCommentBindingModel {

    @NotBlank
    @Size(min = 10)
    private String message;

    public AddCommentBindingModel() {
    }

    public String getMessage() {
        return message;
    }

    public AddCommentBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
