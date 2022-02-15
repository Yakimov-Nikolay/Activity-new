package activity_new.activity_new.service;

import activity_new.activity_new.model.binding.AddActivityBindingModel;
import activity_new.activity_new.model.entity.ActivityEntity;
import activity_new.activity_new.model.service.ActivityServiceModel;
import java.util.*;

public interface ActivityService {
    ActivityServiceModel addActivity(AddActivityBindingModel addActivityBindingModel, String userIdentifier);

    List<ActivityEntity> findAllActivitiesViewModels();
}
