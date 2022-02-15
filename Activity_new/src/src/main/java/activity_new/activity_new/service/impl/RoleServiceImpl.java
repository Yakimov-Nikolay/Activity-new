package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.RoleEntity;
import activity_new.activity_new.model.entity.enumerated.RoleEnumName;
import activity_new.activity_new.repository.RoleRepository;
import activity_new.activity_new.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRole() {

        if (roleRepository.count() != 0) {
            return;
        }
        RoleEntity admin = new RoleEntity();
        admin.setRole(RoleEnumName.ADMIN);

        RoleEntity moderator = new RoleEntity();
        moderator.setRole(RoleEnumName.MODERATOR);

        RoleEntity user = new RoleEntity();
        user.setRole(RoleEnumName.USER);

        roleRepository.saveAll(List.of(admin, moderator, user));
    }
}
