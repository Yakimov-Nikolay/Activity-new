package activity_new.activity_new.web;

import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {


    private final StatisticsService statisticsService;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public AdminController(StatisticsService statisticsService, ActivityRepository activityRepository, UserRepository userRepository) {
        this.statisticsService = statisticsService;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public ModelAndView admin(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stat", statisticsService.getStatistics());
        modelAndView.setViewName("admin_page");

        model.addAttribute("count_of_activity", activityRepository.findAll());
        model.addAttribute("count_of_users", userRepository.findAll());

        return modelAndView;
    }
}
