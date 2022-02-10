package activity_new.activity_new.service;

import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.service.UserServiceModel;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    boolean isUserNameFree(String userName);

    UserEntity findByUsername(String name);
}
