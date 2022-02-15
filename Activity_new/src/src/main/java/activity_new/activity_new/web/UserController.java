package activity_new.activity_new.web;

import activity_new.activity_new.model.binding.UserRegisterBindingModel;
import activity_new.activity_new.model.service.UserServiceModel;
import activity_new.activity_new.service.UserService;
import activity_new.activity_new.service.impl.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final EmailService emailService;

    public UserController(ModelMapper modelMapper, UserService userService, EmailService emailService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
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
        return "/profile";
    }


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
}


