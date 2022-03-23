package activity_new.activity_new.service;

import activity_new.activity_new.model.binding.UploadPictureBindingModel;

import java.io.IOException;

public interface PictureService {

    void uploadPicture(UploadPictureBindingModel uploadPictureBindingModel, String principalName, Long id) throws IOException;

}
