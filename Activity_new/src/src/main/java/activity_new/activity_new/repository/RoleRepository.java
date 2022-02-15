package activity_new.activity_new.repository;

import activity_new.activity_new.model.entity.RoleEntity;
import activity_new.activity_new.model.entity.enumerated.RoleEnumName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(RoleEnumName roleEnumName);
}
