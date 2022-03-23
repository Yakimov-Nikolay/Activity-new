package activity_new.activity_new.model.view;

import java.time.LocalDateTime;

public class CommentViewModel {

    private Long commentId;
    private String user;
    private String message;
    private LocalDateTime created;
    private Boolean canDelete;
    private Boolean canApprove;

    public Long getCommentId() {
        return commentId;
    }

    public CommentViewModel setCommentId(Long commentId) {
        this.commentId = commentId;
        return this;
    }

    public String getUser() {
        return user;
    }

    public CommentViewModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentViewModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public CommentViewModel setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public Boolean getCanApprove() {
        return canApprove;
    }

    public CommentViewModel setCanApprove(Boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }
}
