package activity_new.activity_new.service;
import java.util.*;

import activity_new.activity_new.model.service.CommentServiceModel;
import activity_new.activity_new.model.view.CommentViewModel;
import activity_new.activity_new.service.exception.ObjectNotFoundException;

public interface CommentService {

    CommentViewModel createComment(CommentServiceModel commentServiceModel) throws ObjectNotFoundException;

    List<CommentViewModel> getComments(Long activityId) throws ObjectNotFoundException;
}
