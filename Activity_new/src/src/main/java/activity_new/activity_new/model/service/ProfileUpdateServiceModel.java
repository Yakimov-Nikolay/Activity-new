package activity_new.activity_new.model.service;

import java.time.LocalDateTime;

public class ProfileUpdateServiceModel {

    private Long id;
    private Integer age;
    private Integer phone;
    private LocalDateTime birthday;
    private String description;
    private boolean canDelete;

    public ProfileUpdateServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public ProfileUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ProfileUpdateServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getPhone() {
        return phone;
    }

    public ProfileUpdateServiceModel setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public ProfileUpdateServiceModel setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public ProfileUpdateServiceModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
