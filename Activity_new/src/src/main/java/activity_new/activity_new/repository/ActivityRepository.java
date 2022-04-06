package activity_new.activity_new.repository;

import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity,Long> {

    @Query("select a from ActivityEntity a where a.author.username = ?1")
    List<ActivityEntity> findActivityEntitiesByAuthorUsername(String username);


    List<ActivityEntity> findAll();
}
