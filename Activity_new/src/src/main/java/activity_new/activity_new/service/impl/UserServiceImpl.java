package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.GenderEntity;
import activity_new.activity_new.model.entity.RoleEntity;
import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.model.entity.enumerated.GenderEnumName;
import activity_new.activity_new.model.entity.enumerated.RoleEnumName;
import activity_new.activity_new.model.service.ProfileUpdateServiceModel;
import activity_new.activity_new.model.service.UserServiceModel;
import activity_new.activity_new.model.view.ProfileDetailsViewModel;
import activity_new.activity_new.repository.GenderRepository;
import activity_new.activity_new.repository.RoleRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.GenderService;
import activity_new.activity_new.service.UserService;
import activity_new.activity_new.service.exception.ObjectNotFoundException;
import activity_new.activity_new.service.exception.UserNotFoundEx;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ActivitySerImpl activitySer;
    private final EmailService emailService;
    private final GenderService genderService;
    private final GenderRepository genderRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder, RoleRepository roleRepository, ActivitySerImpl activitySer, EmailService emailService, GenderService genderService, GenderRepository genderRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.activitySer = activitySer;
        this.emailService = emailService;
        this.genderService = genderService;
        this.genderRepository = genderRepository;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

//        RoleEntity adminRole = roleRepository.findByRole(RoleEnumName.ADMIN);
//        RoleEntity moderatorRole = roleRepository.findByRole(RoleEnumName.MODERATOR);
        RoleEntity userRole = roleRepository.findByRole(RoleEnumName.USER);

//        UserEntity user = null;
//        if (userRepository.count() == 0) {
//            user = modelMapper.map(userServiceModel, UserEntity.class);
//            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
//                    .setRoles(Set.of(adminRole, moderatorRole, userRole));
//            user.setCreatedPr(LocalDateTime.now());
//            user.setSex(genderRepository.findBySex(userServiceModel.getSex().getSex()));
//
//        } else if (userRepository.count() == 1) {
//            user = modelMapper.map(userServiceModel, UserEntity.class);
//            user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
//                    .setRoles(Set.of(moderatorRole, userRole));
//            user.setCreatedPr(LocalDateTime.now());
//            user.setSex(genderRepository.findBySex(userServiceModel.getSex().getSex()));
        //}
        //
        UserEntity user = new UserEntity();
        user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setRoles(Set.of(userRole));
        user.setCreatedPr(LocalDateTime.now());
        user.setSex(genderRepository.findBySex(userServiceModel.getSex().getSex()));
        String randomCode = RandomString.make(12);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);


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

    @Override
    public ProfileDetailsViewModel findProfileById(Long id, String name) {
        return this.userRepository
                .findById(id)
                .map(p -> mapDetails(name, p)).get();
    }


    public void updateProfile(ProfileUpdateServiceModel profileUpdateServiceModel) throws ObjectNotFoundException {
        UserEntity userEntity = userRepository.findById(profileUpdateServiceModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("User whit id " + profileUpdateServiceModel.getId() + " not found!!!"));

        userEntity.setBirthday(profileUpdateServiceModel.getBirthday());
        userEntity.setPhone(profileUpdateServiceModel.getPhone());
        userEntity.setDescription(profileUpdateServiceModel.getDescription());
        userEntity.setAge(profileUpdateServiceModel.getAge());

        userRepository.save(userEntity);
    }

    @Override
    public void initializeUsersAndRoles() {
        if (userRepository.count() == 0) {

            RoleEntity adminRole = roleRepository.findByRole(RoleEnumName.ADMIN);
            RoleEntity moderatorRole = roleRepository.findByRole(RoleEnumName.MODERATOR);
            RoleEntity userRole = roleRepository.findByRole(RoleEnumName.USER);

            GenderEntity male = genderRepository.findBySex(GenderEnumName.MALE);
            GenderEntity female = genderRepository.findBySex(GenderEnumName.FEMALE);



            UserEntity Nikolay = new UserEntity();
            String verification = RandomString.make(12);
            Nikolay
                    .setUsername("Niki")
                    .setPassword(passwordEncoder.encode("Test1234"))
                    .setAge(36)
                    .setEmail("yakimov099@gmail.com")
                    .setFirstName("Nikolay")
                    .setLastName("Yakimov")
                    .setSex(male)
                    .setCreatedPr(LocalDateTime.now())
                    .setVerificationCode(verification);

            Nikolay.setRoles(Set.of(adminRole, userRole, moderatorRole));
            userRepository.save(Nikolay);


            UserEntity Lili = new UserEntity();
            String verification1 = RandomString.make(12);
            Lili
                    .setUsername("Lilia")
                    .setPassword(passwordEncoder.encode("Test1234"))
                    .setAge(37)
                    .setEmail("lilia099@gmail.com")
                    .setFirstName("lilia")
                    .setLastName("Petrova")
                    .setSex(female)
                    .setCreatedPr(LocalDateTime.now())
                    .setVerificationCode(verification1);


            Lili.setRoles(Set.of(userRole, moderatorRole));
            userRepository.save(Lili);

        }
    }

    @Override
    public void updatePassword() {

    }


    private ProfileDetailsViewModel mapDetails(String name, UserEntity profile) {
        return this.modelMapper.map(profile, ProfileDetailsViewModel.class);
    }

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundEx {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity != null){
            userEntity.setResetPasswordToken(token);
            userRepository.save(userEntity);
        }else {
            throw new UserNotFoundEx("Could not find any customer with the email " + email);
        }
    }

    public UserEntity getByResetToken(String token){
       return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(UserEntity customer, String newPassword){
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder1.encode(newPassword);
        customer.setPassword(encodedPassword);

        customer.setResetPasswordToken(null);
        userRepository.save(customer);
    }

}
