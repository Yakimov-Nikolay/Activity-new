package activity_new.activity_new.model.entity;

import activity_new.activity_new.model.entity.enumerated.RoleEnumName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    private RoleEnumName role;

    public RoleEntity() {
    }
    @Enumerated(EnumType.STRING)
    public RoleEnumName getRole() {
        return role;
    }

    public RoleEntity setRole(RoleEnumName role) {
        this.role = role;
        return this;
    }
}
