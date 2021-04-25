import controller.InMemoryMatchController;
import controller.MatchController;
import events.*;
import java.util.*;

public class CricketScoreboardApp {
    public static void main(String args[]) {
        MatchController controller = InMemoryMatchController.instance;

        //create teams
        List<String> teamAPlayers = new LinkedList<>();
        List<String> teamBPlayers = new LinkedList<>();
        teamAPlayers.add("P1");
        teamAPlayers.add("P2");
        teamAPlayers.add("P3");
        teamAPlayers.add("P4");
        teamAPlayers.add("P5");

        teamBPlayers.add("P6");
        teamBPlayers.add("p7");
        teamBPlayers.add("P8");
        teamBPlayers.add("P9");
        teamBPlayers.add("P10");

        String team1Id = "Team 1", team2Id="Team 2", matchId = team1Id+"_"+team2Id;
        
        controller
                .handle(new CreateTeamEvent(team1Id, teamAPlayers))
                .handle(new CreateTeamEvent(team2Id, teamBPlayers))
                .handle(new StartMatchEvent(team1Id, team2Id, 2))
                .handle(new StartNewInning(matchId, team1Id, team2Id, teamAPlayers, teamBPlayers))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "2"))
                .handle(new StartNewOverEvent(matchId))
                .handle(new BallEvent(matchId, "W"))
                .handle(new BallEvent(matchId, "4F"))
                .handle(new BallEvent(matchId, "4F"))
                .handle(new BallEvent(matchId, "WB"))
                .handle(new BallEvent(matchId, "CW"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "6S"))
                .handle(new StartNewInning(matchId, team2Id, team1Id, teamBPlayers, teamAPlayers))
                .handle(new BallEvent(matchId, "4F"))
                .handle(new BallEvent(matchId, "6S"))
                .handle(new BallEvent(matchId, "CW"))
                .handle(new BallEvent(matchId, "CW"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new StartNewOverEvent(matchId))
                .handle(new BallEvent(matchId, "6S"))
                .handle(new BallEvent(matchId, "1"))
                .handle(new BallEvent(matchId, "HW"))
                .handle(new BallEvent(matchId, "SW"))
                .handle(new MatchEndEvent(matchId));
    }
}
