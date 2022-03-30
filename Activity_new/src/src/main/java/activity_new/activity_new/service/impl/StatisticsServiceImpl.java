package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.view.StatisticsViewModel;
import activity_new.activity_new.service.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private int anonymousRequests, authRequests;

    @Override
    public void onRequests() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null && (authentication.getPrincipal() instanceof UserDetails)) {
            authRequests++;
        } else {
            anonymousRequests++;
        }

    }

    @Override
    public StatisticsViewModel getStatistics() {
        return new StatisticsViewModel(authRequests, anonymousRequests);
    }
}
