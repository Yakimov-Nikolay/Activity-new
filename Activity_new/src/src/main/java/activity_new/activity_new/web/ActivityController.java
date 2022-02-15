package activity_new.activity_new.web;

import activity_new.activity_new.model.binding.AddActivityBindingModel;
import activity_new.activity_new.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ActivityController {

    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    public ActivityController(ActivityService activityService, ModelMapper modelMapper) {
        this.activityService = activityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String all(Model model) {

        model.addAttribute("allVideoActivities", activityService.findAllActivitiesViewModels());
        return "redirect:/allActivities";
    }

    @GetMapping("/add")
    public String add() {
        return "addActivities";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddActivityBindingModel addActivityBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addActivityBindingModel", addActivityBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addActivityBindingModel", bindingResult);
            return "redirect:/addActivities";
        }

        activityService.addActivity(addActivityBindingModel, principal.getName());

        return "redirect:/";
    }

    @ModelAttribute
    public AddActivityBindingModel addActivityBindingModel() {
        return new AddActivityBindingModel();
    }

}
