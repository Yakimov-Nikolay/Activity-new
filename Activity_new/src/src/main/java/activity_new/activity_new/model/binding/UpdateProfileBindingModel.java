package activity_new.activity_new.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UpdateProfileBindingModel {

//    private Long id;
    private Integer age;
    private Integer phone;
    private LocalDateTime birthday;
    private String description;

    public UpdateProfileBindingModel() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public UpdateProfileBindingModel setId(Long id) {
//        this.id = id;
//        return this;
//    }

    @DecimalMin("10")
    public Integer getAge() {
        return age;
    }

    public UpdateProfileBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Size(min = 10)
    public Integer getPhone() {
        return phone;
    }

    public UpdateProfileBindingModel setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime getBirthday() {
        return birthday;
    }

    public UpdateProfileBindingModel setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    @Size(min = 10, message = "Description must have min 10 symbols")
    public String getDescription() {
        return description;
    }

    public UpdateProfileBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}

