package activity_new.activity_new.web;

import activity_new.activity_new.service.UserService;
import activity_new.activity_new.service.exception.UserNotFoundEx;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;


@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;
    private UserService userService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try{
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (UserNotFoundEx ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "forgot_password_form";
    }

    private void sendEmail(String email, String resetPasswordLink) {

    }

    public class Utility {
        public static String getSiteURL(HttpServletRequest request) {
            String siteURL = request.getRequestURL().toString();
            return siteURL.replace(request.getServletPath(), "");
        }
    }
}
