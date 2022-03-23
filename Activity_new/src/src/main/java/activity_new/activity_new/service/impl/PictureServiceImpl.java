package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.binding.UploadPictureBindingModel;
import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.PictureEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.PictureRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.CloudinaryService;
import activity_new.activity_new.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(ActivityRepository activityRepository, UserRepository userRepository, PictureRepository pictureRepository, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void uploadPicture(UploadPictureBindingModel uploadPictureBindingModel, String principalName, Long id) throws IOException {

        UserEntity userEntity = userRepository.findByUsername(principalName).orElseThrow();
        ActivityEntity activityEntity = activityRepository.findById(id).orElseThrow();

        PictureEntity pictureEntity = createPicture(uploadPictureBindingModel.getPicture()
                , uploadPictureBindingModel.getTitle()
                , uploadPictureBindingModel.getDescription());
        pictureEntity.setAuthor(userEntity);
        pictureEntity.setActivity(activityEntity);

        pictureRepository.save(pictureEntity);
    }


    private PictureEntity createPicture(MultipartFile picture, String title,
                                        String description) throws IOException {

        final CloudinaryImage upload = this.cloudinaryService.upload(picture);

        return new PictureEntity()
                .setPublicId(upload.getPublicId())
                .setTitle(title)
                .setUrl(upload.getUrl())
                .setDescription(description);
    }
}
