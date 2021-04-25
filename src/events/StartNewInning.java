package events;

import java.util.List;

public class StartNewInning extends BaseEvent {
    private final String matchId,  battingTeam,  bowlingTeam;
    private final List<String> battingOrder,  bowlingOrder;

    public StartNewInning(String matchId, String battingTeam, String bowlingTeam, List<String> battingOrder, List<String> bowlingOrder) {
        this.matchId = matchId;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.battingOrder = battingOrder;
        this.bowlingOrder = bowlingOrder;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public List<String> getBattingOrder() {
        return battingOrder;
    }


    public List<String> getBowlingOrder() {
        return bowlingOrder;
    }
}
