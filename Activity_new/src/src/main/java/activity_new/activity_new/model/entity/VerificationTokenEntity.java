package activity_new.activity_new.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_code")
public class VerificationTokenEntity extends BaseEntity {

    private static final int EXPIRATION = 60 * 24;

    private String token;
    private Date expiryDate;
    private UserEntity userEntity;

    public VerificationTokenEntity() {
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);

        return new Date(calendar.getTime().getTime());
    }



    public Date getExpiryDate() {
        return expiryDate;
    }

    public VerificationTokenEntity setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getToken() {
        return token;
    }

    public VerificationTokenEntity setToken(String token) {
        this.token = token;
        return this;
    }

    @OneToOne(fetch = FetchType.EAGER)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public VerificationTokenEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }
}
