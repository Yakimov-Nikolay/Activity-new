package activity_new.activity_new.service;

import activity_new.activity_new.model.binding.UploadPictureBindingModel;
import activity_new.activity_new.model.entity.PictureEntity;
import activity_new.activity_new.model.view.PictureViewModel;
import org.springframework.data.jpa.repository.Query;

import java.io.IOException;
import java.util.List;

public interface PictureService {

    void uploadPicture(UploadPictureBindingModel uploadPictureBindingModel, String principalName, Long id) throws IOException;

    List<PictureEntity> findAllByActivityId(Long id);
}
