package activity_new.activity_new.repository;

import activity_new.activity_new.model.entity.GenderEntity;
import activity_new.activity_new.model.entity.enumerated.GenderEnumName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {

    GenderEntity findBySex(GenderEnumName sex);
}
