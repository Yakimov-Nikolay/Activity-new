package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.binding.AddActivityBindingModel;
import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.service.ActivityServiceModel;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(UserRepository userRepository, ActivityRepository activityRepository,
                               ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ActivityServiceModel addActivity(AddActivityBindingModel addActivityBindingModel,
                                            String userIdentifier) {

        UserEntity userEntity = userRepository.findByUsername(userIdentifier).orElseThrow();

        modelMapper.map(addActivityBindingModel, ActivityServiceModel.class);

        ActivityEntity newActivity = modelMapper.map(addActivityBindingModel, ActivityEntity.class);

        newActivity.setAuthor(userEntity);
        newActivity.setCreated(LocalDateTime.now());
        ActivityEntity save = activityRepository.save(newActivity);

        return modelMapper.map(save, ActivityServiceModel.class);
    }

    @Transactional
    @Override
    public List<ActivityEntity> findAllActivitiesViewModels() {
        return activityRepository.findAll()
                .stream()
                .map(activity->modelMapper.map(activity, ActivityEntity.class))
                .collect(Collectors.toList());
    }
}
