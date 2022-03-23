package activity_new.activity_new.model.service;

import activity_new.activity_new.model.entity.GenderEntity;
import activity_new.activity_new.model.entity.RoleEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel{


    private String username;
    private String firstName;
    private String lastName;
    private GenderEntity sex;
    private Integer age;
    private String password;
    private String email;
    private Integer phone;
    private LocalDateTime birthday;
    private String description;
    private LocalDateTime createdPr;
    private Set<RoleEntity> roles = new HashSet<>();

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public GenderEntity getSex() {
        return sex;
    }

    public UserServiceModel setSex(GenderEntity sex) {
        this.sex = sex;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPhone() {
        return phone;
    }

    public UserServiceModel setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public UserServiceModel setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedPr() {
        return createdPr;
    }

    public UserServiceModel setCreatedPr(LocalDateTime createdPr) {
        this.createdPr = createdPr;
        return this;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
