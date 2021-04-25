package events;

public class StartNewOverEvent extends BaseEvent {
    private final String matchId;

    public StartNewOverEvent(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }
}
