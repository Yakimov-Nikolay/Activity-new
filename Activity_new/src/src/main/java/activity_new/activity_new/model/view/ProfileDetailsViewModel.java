package activity_new.activity_new.model.view;

import java.time.LocalDateTime;

public class ProfileDetailsViewModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String sex;
    private Integer age;
    private String email;
    private Integer phone;
    private LocalDateTime birthday;
    private String description;
    private LocalDateTime createdPr;

    public ProfileDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ProfileDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfileDetailsViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileDetailsViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileDetailsViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public ProfileDetailsViewModel setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ProfileDetailsViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileDetailsViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPhone() {
        return phone;
    }

    public ProfileDetailsViewModel setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public ProfileDetailsViewModel setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProfileDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedPr() {
        return createdPr;
    }

    public ProfileDetailsViewModel setCreatedPr(LocalDateTime createdPr) {
        this.createdPr = createdPr;
        return this;
    }
}
