package activity_new.activity_new.model.binding;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    private static final String EMAIL_VERIFICATION =
            "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private static final String PASSWORD_VERIFICATION =
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBindingModel() {
    }

    @NotBlank
    @Size(min = 4, max = 50)
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Size(min = 2, max = 50)
    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank
    @Size(min = 2, max = 50)
    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotBlank
    @Pattern(regexp = PASSWORD_VERIFICATION)
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    @Pattern(regexp = PASSWORD_VERIFICATION)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @NotBlank
    @Pattern(regexp = EMAIL_VERIFICATION)
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
