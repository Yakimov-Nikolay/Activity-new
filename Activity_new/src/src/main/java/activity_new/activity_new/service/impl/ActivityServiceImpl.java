package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.binding.AddActivityBindingModel;
import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.RoleEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.entity.enumerated.RoleEnumName;
import activity_new.activity_new.model.service.ActivityServiceModel;
import activity_new.activity_new.model.view.ActivityFullDetailsViewModel;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.PictureRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    public ActivityServiceImpl(UserRepository userRepository, PictureRepository pictureRepository,
                               ActivityRepository activityRepository,
                               ModelMapper modelMapper,
                               EmailService emailService) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
        this.emailService = emailService;
    }

    @Override
    public ActivityServiceModel addActivity(AddActivityBindingModel addActivityBindingModel,
                                            String userIdentifier) {

        UserEntity userEntity = userRepository.findByUsername(userIdentifier).orElseThrow();

        modelMapper.map(addActivityBindingModel, ActivityServiceModel.class);

        ActivityEntity newActivity = modelMapper.map(addActivityBindingModel, ActivityEntity.class);

        newActivity.setAuthor(userEntity);
        newActivity.setCreated(LocalDateTime.now());
        newActivity.setLikeVideoCounter(0L);
        newActivity.setDislikeVideoCounter(0L);
        ActivityEntity save = activityRepository.save(newActivity);

        return modelMapper.map(save, ActivityServiceModel.class);
    }

    @Override
    public List<ActivityEntity> findAllActivitiesViewModels() {
        return activityRepository
                .findAll()
                .stream()
                .map(a -> modelMapper.map(a, ActivityEntity.class))
                .collect(Collectors.toList());
    }

    @Override
    public ActivityFullDetailsViewModel findById(Long id, String currentUser) {
        return this.activityRepository.findById(id)
                .map(a -> mapDetails(currentUser, a)).get();
    }

    private ActivityFullDetailsViewModel mapDetails(String currentUser, ActivityEntity a) {
        ActivityFullDetailsViewModel activityFullDetailsViewModel = this.modelMapper.map(a, ActivityFullDetailsViewModel.class);
        activityFullDetailsViewModel.setCanDelete(isOwner(currentUser, a.getId()));
        activityFullDetailsViewModel.setTitle(a.getTitle());
        activityFullDetailsViewModel.setPictures(a.getPictures());
        return activityFullDetailsViewModel;
    }

    public boolean isOwner(String currentUser, Long id) {
        Optional<ActivityEntity> activityEntityOptional = activityRepository.findById(id);
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(currentUser);

        if (activityEntityOptional.isEmpty() || userEntityOptional.isEmpty()) {
            return false;
        } else {
            ActivityEntity activityEntity = activityEntityOptional.get();

            return isAdmin(userEntityOptional.get())
                    || activityEntity.getAuthor()
                    .getUsername()
                    .equals(currentUser);

        }

    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<ActivityEntity> findActivityEntitiesByAuthorUsername(String name) {
        return new ArrayList<>(activityRepository.findActivityEntitiesByAuthorUsername(name));
    }

    @Override
    public void addLike(Long id) {
        Optional<ActivityEntity> activityEntity = Optional.of(activityRepository.findById(id).orElseThrow());

        activityRepository
                .save(activityEntity.get().setLikeVideoCounter(activityEntity.get().getDislikeVideoCounter() + 1));
    }

    @Override
    public void addDislike(Long id) {
        Optional<ActivityEntity> activityEntity = Optional.of(activityRepository.findById(id).orElseThrow());
        activityRepository
                .save(activityEntity.get().setDislikeVideoCounter(activityEntity.get().getDislikeVideoCounter() + 1));
    }


    public ActivityEntity findActivityEntityByLikeVideoCounter() {
         return  activityRepository.findAll()
                 .stream()
                 .max((a,b) -> (int) (a.getLikeVideoCounter() - b.getLikeVideoCounter()))
                 .get();

    }



    private boolean isAdmin(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .anyMatch(r -> r == RoleEnumName.ADMIN);
    }


}
