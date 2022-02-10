package activity_new.activity_new.model.service;

import activity_new.activity_new.model.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel{

    private String username;
    private String firstName;
    private String lastName;
//    private String sex;
//    private Integer age;
    private String password;
    private String email;
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

//    public String getSex() {
//        return sex;
//    }
//
//    public UserServiceModel setSex(String sex) {
//        this.sex = sex;
//        return this;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public UserServiceModel setAge(int age) {
//        this.age = age;
//        return this;
//    }

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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
