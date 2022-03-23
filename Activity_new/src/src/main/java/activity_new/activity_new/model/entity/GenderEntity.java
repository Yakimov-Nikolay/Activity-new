package activity_new.activity_new.model.entity;

import activity_new.activity_new.model.entity.enumerated.GenderEnumName;

import javax.persistence.*;

@Entity
@Table(name = "gender")
public class GenderEntity extends BaseEntity {

    private GenderEnumName sex;

    public GenderEntity() {
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    public GenderEnumName getSex() {
        return sex;
    }

    public GenderEntity setSex(GenderEnumName sex) {
        this.sex = sex;
        return this;
    }
}
