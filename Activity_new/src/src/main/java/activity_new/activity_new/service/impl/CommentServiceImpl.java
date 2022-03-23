package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.CommentEntity;
import activity_new.activity_new.model.service.CommentServiceModel;
import activity_new.activity_new.model.view.CommentViewModel;
import activity_new.activity_new.repository.ActivityRepository;
import activity_new.activity_new.repository.CommentRepository;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.CommentService;
import activity_new.activity_new.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(ActivityRepository activityRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) throws ObjectNotFoundException {

        Objects.requireNonNull(commentServiceModel.getAuthor());

        var activity = activityRepository.findById(commentServiceModel.getActivityId())
                .orElseThrow(() -> new ObjectNotFoundException("Activity whit id " + commentServiceModel.getActivityId() + " not fount!!!"));

        var author = userRepository.findByUsername(commentServiceModel.getAuthor())
                .orElseThrow(() -> new ObjectNotFoundException("User whit username " + commentServiceModel.getAuthor() + " not fount!!!"));

        CommentEntity newComment = new CommentEntity();

        newComment.setApproved(false);
        newComment.setContent(commentServiceModel.getMessage());
        newComment.setAuthor(author);
        newComment.setActivity(activity);
        newComment.setCreated(LocalDateTime.now());

        CommentEntity savedComment = commentRepository.save(newComment);

        return mapAsComment(savedComment);

    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long activityId) throws ObjectNotFoundException {

        var activityOpt = activityRepository.findById(activityId);

        if (activityOpt.isEmpty()) {
            throw new ObjectNotFoundException("Activity whit id " + activityId + " is not found!");
        }
        return activityOpt.get()
                .getCommentEntityList()
                .stream()
                .map(this::mapAsComment)
                .collect(Collectors.toList());
    }

    private CommentViewModel mapAsComment(CommentEntity commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();
        commentViewModel
                .setCommentId(commentEntity.getId())
                .setCanApprove(true)
                .setCanDelete(true)
                .setMessage(commentEntity.getContent())
                .setCreated(commentEntity.getCreated())
                .setUser(commentEntity.getAuthor().getUsername());

        return commentViewModel;
    }
}
