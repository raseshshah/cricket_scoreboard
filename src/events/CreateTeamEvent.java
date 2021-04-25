package events;

import java.util.List;

public final class CreateTeamEvent extends BaseEvent {
    private final String teamName;
    private final List<String> playerIds;

    public CreateTeamEvent(String teamName, List<String> playerIds) {
        this.teamName = teamName;
        this.playerIds = playerIds;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<String> getPlayerIds() {
        return playerIds;
    }
}
