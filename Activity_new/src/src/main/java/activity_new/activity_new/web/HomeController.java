package activity_new.activity_new.web;

import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final ActivityService activityService;

    public HomeController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        model.addAttribute("user_home", activityService.findActivityEntitiesByAuthorUsername(principal.getName()));
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
