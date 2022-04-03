package activity_new.activity_new.web;

import activity_new.activity_new.model.binding.AddActivityBindingModel;
import activity_new.activity_new.service.ActivityService;
import activity_new.activity_new.service.PictureService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ActivityController {

    private final ActivityService activityService;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;

    public ActivityController(ActivityService activityService, ModelMapper modelMapper, PictureService pictureService) {
        this.activityService = activityService;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
    }

    @GetMapping("/all")
    public String allActivity(Model model) {

        model.addAttribute("videoActivities", activityService.findAllActivitiesViewModels());
        return "/allActivities";
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

    @GetMapping("/{id}/details")
    public String details(@PathVariable Long id,
                          Model model, @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("detail", this.activityService.findById(id, principal.getUsername()));
        model.addAttribute("details_with_pic", pictureService.findAllByActivityId(id));

        return "activity_details";
    }

    @PreAuthorize("isOwner(#id)")
    @DeleteMapping("/{id}/details")
    public String delete(@PathVariable Long id) {

        activityService.deleteActivity(id);

        return "/allActivities";

    }

    @ModelAttribute
    public AddActivityBindingModel addActivityBindingModel() {
        return new AddActivityBindingModel();
    }
}
