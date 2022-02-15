package activity_new.activity_new.config;

import activity_new.activity_new.service.ActivityService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class ActivityMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
private final ActivityService activityService;

    public ActivityMethodSecurityExpressionHandler(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {


        return super.createSecurityExpressionRoot(authentication, invocation);
    }
}
