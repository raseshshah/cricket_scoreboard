package events;

import model.Ball;

public class BallEvent extends BaseEvent {
    private final String value, matchId;

    public BallEvent(String matchId, String value) {
        this.value = value;
        this.matchId = matchId;
    }

    public String getValue() {
        return value;
    }

    public String getMatchId() {
        return matchId;
    }
}
