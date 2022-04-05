package activity_new.activity_new.repository;

import activity_new.activity_new.model.entity.PictureEntity;
import activity_new.activity_new.model.view.PictureViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    @Query("select p from PictureEntity p where p.activity.id = ?1")
    List<PictureEntity> findAllByActivity_Id(Long id);

    void deleteByPublicId(String publicId);

}
