package activity_new.activity_new.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    //    private String sex;
//    private int age;
    private String password;
    private String email;
    private Set<RoleEntity> roles = new HashSet<>();


    public UserEntity() {
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    //    public String getSex() {
//        return sex;
//    }
//
//    public UserEntity setSex(String sex) {
//        this.sex = sex;
//        return this;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public UserEntity setAge(int age) {
//        this.age = age;
//        return this;
//    }
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

}
