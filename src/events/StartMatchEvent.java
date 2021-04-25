package events;

public final class StartMatchEvent extends BaseEvent {
    private final String homeTeamId, awayTeamId, id;
    private final int overs;

    public StartMatchEvent(String homeTeamId, String awayTeamId, int overs) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        id = homeTeamId+"_"+awayTeamId;
        this.overs = overs;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public int getOvers() {
        return overs;
    }

    public String getId() {
        return id;
    }
}
