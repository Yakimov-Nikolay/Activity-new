package activity_new.activity_new.service;

import activity_new.activity_new.model.binding.AddActivityBindingModel;
import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.service.ActivityServiceModel;
import activity_new.activity_new.model.view.ActivityFullDetailsViewModel;

import java.util.*;

public interface ActivityService {
    ActivityServiceModel addActivity(AddActivityBindingModel addActivityBindingModel, String userIdentifier);
    List<ActivityEntity> findAllActivitiesViewModels();
    ActivityFullDetailsViewModel findById(Long id, String name);
    boolean isOwner(String userName, Long id);
    void deleteActivity(Long id);
    List<ActivityEntity> findActivityEntitiesByAuthorUsername(String name);
    void addLike(Long id);
    void addDislike(Long id);

    ActivityEntity findActivityEntityByLikeVideoCounter();

}

