package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.RoleEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.entity.enumerated.RoleEnumName;
import activity_new.activity_new.model.service.UserServiceModel;
import activity_new.activity_new.repository.RoleRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ActivitySerImpl activitySer;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, RoleRepository roleRepository, ActivitySerImpl activitySer, EmailService emailService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.activitySer = activitySer;
        this.emailService = emailService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        RoleEntity adminRole = roleRepository.findByRole(RoleEnumName.ADMIN);
        RoleEntity moderatorRole = roleRepository.findByRole(RoleEnumName.MODERATOR);
        RoleEntity userRole = roleRepository.findByRole(RoleEnumName.USER);

        UserEntity user = null;
        if (userRepository.count() == 0) {
            user = modelMapper.map(userServiceModel, UserEntity.class);
            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                    .setRoles(Set.of(adminRole, moderatorRole, userRole));
            user.setCreatedPr(LocalDateTime.now());

        } else if (userRepository.count() == 1) {
            user = modelMapper.map(userServiceModel, UserEntity.class);
            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                    .setRoles(Set.of(moderatorRole, userRole));
            user.setCreatedPr(LocalDateTime.now());
        } else {
            user = modelMapper.map(userServiceModel, UserEntity.class);
            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                    .setRoles(Set.of(userRole));
            user.setCreatedPr(LocalDateTime.now());
        }


        emailService.sendSimpleMessage(user.getEmail(),
                "Registration in Activity",
                "Congratulation " + user.getUsername() + " you are part of our web site");
        userRepository.save(user);


        UserDetails principal = activitySer.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @Override
    public boolean isUserNameFree(String userName) {
        return userRepository.findByUsernameIgnoreCase(userName).isEmpty();
    }

    @Override
    public UserEntity findByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }
}
