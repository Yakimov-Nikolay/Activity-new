package activity_new.activity_new.repository;

import activity_new.activity_new.model.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity,Long> {
}
