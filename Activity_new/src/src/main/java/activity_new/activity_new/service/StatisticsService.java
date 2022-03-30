package activity_new.activity_new.service;

import activity_new.activity_new.model.view.StatisticsViewModel;

public interface StatisticsService {
    void onRequests();

    StatisticsViewModel getStatistics();
}
