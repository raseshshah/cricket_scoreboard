package events;

public class MatchEndEvent extends BaseEvent {
    private final String matchId;

    public MatchEndEvent(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }
}
