package activity_new.activity_new.init;

import activity_new.activity_new.service.ActivityService;
import activity_new.activity_new.service.GenderService;
import activity_new.activity_new.service.RoleService;
import activity_new.activity_new.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final RoleService roleService;
    private final GenderService genderService;
    private final UserService userService;
    private final ActivityService activityService;

    public DataInit(RoleService roleService, GenderService genderService, UserService userService, ActivityService activityService) {
        this.roleService = roleService;
        this.genderService = genderService;
        this.userService = userService;
        this.activityService = activityService;
    }

    @Override
    public void run(String... args) {
        roleService.initRole();
        genderService.initGender();
        userService.initializeUsersAndRoles();
        activityService.initActivity();


    }
}
