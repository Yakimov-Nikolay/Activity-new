package activity_new.activity_new.web;

import activity_new.activity_new.model.binding.UpdateProfileBindingModel;
import activity_new.activity_new.model.binding.UserRegisterBindingModel;
import activity_new.activity_new.model.service.ProfileUpdateServiceModel;
import activity_new.activity_new.model.service.UserServiceModel;
import activity_new.activity_new.model.view.ProfileDetailsViewModel;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.service.ActivityService;
import activity_new.activity_new.service.UserService;
import activity_new.activity_new.service.exception.ObjectNotFoundException;
import activity_new.activity_new.service.impl.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ActivityRepository activityRepository;
    private final ActivityService activityService;
    private final EmailService emailService;

    public UserController(ModelMapper modelMapper, UserService userService, ActivityRepository activityRepository, ActivityService activityService, EmailService emailService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.activityRepository = activityRepository;
        this.activityService = activityService;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "register";
        }
        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String errorLog(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                           RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("loginError", true);
        redirectAttributes.addFlashAttribute("userError", username);

        return "redirect:/users/login";
    }


    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {

        model.addAttribute("profile", userService.findByUsername(principal.getName()));
        model.addAttribute("count_of_activity", activityRepository.count());

        return "/profile";
    }

    @GetMapping("/profile/{id}/update")
    public String updateProfile(Model model, Principal principal, @PathVariable Long id) {

        ProfileDetailsViewModel profileDetailsViewModel = userService.findProfileById(id, principal.getName());

        UpdateProfileBindingModel updateProfileBindingModel = modelMapper.map(profileDetailsViewModel, UpdateProfileBindingModel.class);

        model.addAttribute("update_profile", updateProfileBindingModel);

        return "/profile_update";
    }

    @PatchMapping("/profile/update")
    public String update(UpdateProfileBindingModel updateProfileBindingModel) throws ObjectNotFoundException {

        ProfileUpdateServiceModel profileUpdateServiceModel =
                modelMapper.map(updateProfileBindingModel, ProfileUpdateServiceModel.class);
        userService.updateProfile(profileUpdateServiceModel);

        return "redirect:/";
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
}


