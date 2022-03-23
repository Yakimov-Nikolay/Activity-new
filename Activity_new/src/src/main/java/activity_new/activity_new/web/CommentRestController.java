package activity_new.activity_new.web;

import activity_new.activity_new.model.apiValidation.ApiError;
import activity_new.activity_new.model.binding.AddCommentBindingModel;
import activity_new.activity_new.model.service.CommentServiceModel;
import activity_new.activity_new.model.view.CommentViewModel;
import activity_new.activity_new.service.CommentService;
import activity_new.activity_new.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.*;

@RestController
public class CommentRestController {

    private final ModelMapper modelMapper;
    private final CommentService commentService;

    public CommentRestController(ModelMapper modelMapper, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.commentService = commentService;
    }

    @GetMapping("/api/{activityId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long activityId,
            Principal principal) throws ObjectNotFoundException {

        return ResponseEntity.ok(commentService.getComments(activityId));
    }

    @PostMapping("/api/{activityId}/comments")
    public ResponseEntity<CommentViewModel> newComment(@AuthenticationPrincipal UserDetails principal,
                                                       @PathVariable Long activityId
            , @RequestBody @Valid AddCommentBindingModel addCommentBindingModel) throws ObjectNotFoundException {

        CommentServiceModel commentServiceModel =
                modelMapper.map(addCommentBindingModel, CommentServiceModel.class);
        commentServiceModel.setAuthor(principal.getUsername());
        commentServiceModel.setActivityId(activityId);

        CommentViewModel newComment =
                commentService.createComment(commentServiceModel);

        URI locationOfNewComment =
                URI.create(String.format("/api/%s/comments/%s", activityId, newComment.getCommentId()));

        return ResponseEntity.created(locationOfNewComment).body(newComment);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> onValidateFailure(MethodArgumentNotValidException exs) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exs.getFieldErrors().forEach(fieldError -> apiError.addFieldWithError(fieldError.getField()));

        return ResponseEntity.badRequest().body(apiError);

    }
}
