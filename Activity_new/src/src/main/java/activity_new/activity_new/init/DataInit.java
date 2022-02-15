package activity_new.activity_new.init;

import activity_new.activity_new.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final RoleService roleService;

    public DataInit(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {
        roleService.initRole();
    }
}
