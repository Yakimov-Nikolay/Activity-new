package activity_new.activity_new.web.Interceptors;

import activity_new.activity_new.service.StatisticsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class StatisticsInterceptors implements HandlerInterceptor {

    private final StatisticsService statisticsService;

    public StatisticsInterceptors(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        statisticsService.onRequests();

        return true;
    }
}
