package activity_new.activity_new.service;

import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.service.ProfileUpdateServiceModel;
import activity_new.activity_new.model.service.UserServiceModel;
import activity_new.activity_new.model.view.ProfileDetailsViewModel;
import activity_new.activity_new.service.exception.ObjectNotFoundException;
import activity_new.activity_new.service.exception.UserNotFoundEx;

public interface UserService {

    void registerUser(UserServiceModel userServiceModel);

    boolean isUserNameFree(String userName);

    UserEntity findByUsername(String name);

    ProfileDetailsViewModel findProfileById(Long id, String name);

    void updateProfile(ProfileUpdateServiceModel profileUpdateServiceModel) throws ObjectNotFoundException;

    void initializeUsersAndRoles();

    void updateResetPasswordToken(String token, String email) throws UserNotFoundEx;

    UserEntity getByResetToken(String token);

    void updatePassword(UserEntity userEntity, String password);
}

