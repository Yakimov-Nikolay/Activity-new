package activity_new.activity_new.model.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

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

    private VerificationTokenEntity verificationCode;
    private String resetPasswordToken;

    private boolean enabled;

    @OneToOne
    public VerificationTokenEntity getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(VerificationTokenEntity verificationCode) {
        this.verificationCode = verificationCode;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public UserEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Column(length = 12)
    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public UserEntity setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
        return this;
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

    @OneToOne
    public GenderEntity getSex() {
        return sex;
    }

    public UserEntity setSex(GenderEntity sex) {
        this.sex = sex;
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

    @Column
    public LocalDateTime getCreatedPr() {
        return createdPr;
    }

    public UserEntity setCreatedPr(LocalDateTime createdPr) {
        this.createdPr = createdPr;
        return this;
    }

    @Column
    public Integer getPhone() {
        return phone;
    }

    public UserEntity setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public UserEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column
    public LocalDateTime getBirthday() {
        return birthday;
    }

    public UserEntity setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    @DecimalMin("10")
    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
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
