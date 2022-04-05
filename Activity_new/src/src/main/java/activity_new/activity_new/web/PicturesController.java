package activity_new.activity_new.web;

import activity_new.activity_new.model.binding.UploadPictureBindingModel;
import activity_new.activity_new.model.entity.PictureEntity;
import activity_new.activity_new.model.view.PictureViewModel;
import activity_new.activity_new.repository.PictureRepository;
import activity_new.activity_new.service.CloudinaryService;
import activity_new.activity_new.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class PicturesController {


    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;
    private final PictureRepository pictureRepository;

    public PicturesController(CloudinaryService cloudinaryService, PictureService pictureService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/{id}/upload")
    public String upload(@PathVariable Long id) {
        return "/upload_pic";
    }

    @PostMapping("/{id}/upload")
    public String uploadConfirm(@Valid UploadPictureBindingModel uploadPictureBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal, @PathVariable Long id) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("uploadPictureBindingModel", uploadPictureBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.uploadPictureBindingModel", bindingResult);

            return "/upload_pic";
        }
        pictureService.uploadPicture(uploadPictureBindingModel, principal.getName(), id);

        return "redirect:/";
    }
    @Transactional
    @DeleteMapping("/details/delete/picture")
    public String deletePic(@RequestParam("publicId") String publicId) {

        if (cloudinaryService.delete(publicId)){
            pictureRepository.deleteByPublicId(publicId);
        }

        return "redirect:/all";
    }


    @ModelAttribute
    public UploadPictureBindingModel uploadPictureBindingModel() {
        return new UploadPictureBindingModel();
    }
}

