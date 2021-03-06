package activity_new.activity_new.repository;

import activity_new.activity_new.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Collection<Object> findByUsernameIgnoreCase(String userName);

    Optional<Object> findProfileById(Long id);

    List<UserEntity> findAll();
}
