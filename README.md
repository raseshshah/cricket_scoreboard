# cricket_scoreboard

I have tried to create reactive cricket scoreboard app which could support multiple matches concurrently and react on various sequencial events happend during the match.It does have snapshot functionality which could generate immutable score board at any point of time. 


# Sample Reactive app:
```
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

```

# Output:

```
Scorecard for Team 1:
Name	Scores	4s		6s		Balls	Strike Rate
------------------------------------------------------
P1*		3		0		0		3		100%
P2*		4		0		0		3		133%
P3		0		0		0		0		0%
P4		0		0		0		0		0%
P5		0		0		0		0		0%

Scorecard for Team 2:
Name	Wickets	Runs	Overs	Dots	Maiden	Average
-------------------------------------------------------
P6		0		7		1		0		0		0.0
p7		0		0		0		0		0		0.0
P8		0		0		0		0		0		0.0
P9		0		0		0		0		0		0.0
P10		0		0		0		0		0		0.0


Total runs: 7/0
Extras: 0
Overs: 1





Scorecard for Team 1:
Name	Scores	4s		6s		Balls	Strike Rate
------------------------------------------------------
P1		3		0		0		4		75%
P2*		10		0		1		4		250%
P3		8		2		0		3		266%
P4*		1		0		0		1		100%
P5		0		0		0		0		0%

Scorecard for Team 2:
Name	Wickets	Runs	Overs	Dots	Maiden	Average
-------------------------------------------------------
P6		0		7		1		0		0		0.0
p7		2		15		1		0		0		8.0
P8		0		0		0		0		0		0.0
P9		0		0		0		0		0		0.0
P10		0		0		0		0		0		0.0


Total runs: 23/2
Extras: 1
Overs: 2





Scorecard for Team 2:
Name	Scores	4s		6s		Balls	Strike Rate
------------------------------------------------------
P6		10		1		1		3		333%
p7*		1		0		0		1		100%
P8		0		0		0		1		0%
P9*		1		0		0		1		100%
P10		0		0		0		0		0%

Scorecard for Team 1:
Name	Wickets	Runs	Overs	Dots	Maiden	Average
-------------------------------------------------------
P1		2		12		1		0		0		6.0
P2		0		0		0		0		0		0.0
P3		0		0		0		0		0		0.0
P4		0		0		0		0		0		0.0
P5		0		0		0		0		0		0.0


Total runs: 12/2
Extras: 0
Overs: 1





Scorecard for Team 2:
Name	Scores	4s		6s		Balls	Strike Rate
------------------------------------------------------
P6		10		1		1		3		333%
p7*		8		0		1		3		266%
P8		0		0		0		1		0%
P9		1		0		0		2		50%
P10		0		0		0		1		0%

Scorecard for Team 1:
Name	Wickets	Runs	Overs	Dots	Maiden	Average
-------------------------------------------------------
P1		2		12		1		0		0		6.0
P2		2		7		0		2		0		3.0
P3		0		0		0		0		0		0.0
P4		0		0		0		0		0		0.0
P5		0		0		0		0		0		0.0


Total runs: 19/4
Extras: 0
Overs: 1.4





Result: Team 1 won the match by 4 runs
Process finished with exit code 0
```
