package activity_new.activity_new.model.view;

public class StatisticsViewModel {

    private final int authRequests;
    private final int anonymousRequests;

    public StatisticsViewModel(int authRequests, int anonymousRequests) {
        this.authRequests = authRequests;
        this.anonymousRequests = anonymousRequests;
    }
    public int getTotalRequests(){
        return anonymousRequests + authRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }

    public int getAnonymousRequests() {
        return anonymousRequests;
    }
}
