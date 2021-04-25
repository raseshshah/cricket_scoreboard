package test;

import javafx.print.Collation;
import model.Ball;
import model.Player;
import model.Team;
import model.match.CustomFormatCricketMatch;
import model.match.Match;
import org.junit.Test;
import utils.InMemoryScoreBoard;
import utils.ScoreBoard;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class InMemoryScoreBoardTest {
    @Test
    public void test() {
        //create players
        Player pa1 = new Player("P1", "Rasesh", "Shah");
        Player pa2 = new Player("P2", "Pinal", "Shah");
        Player pa3 = new Player("P3", "Dipak", "Shah");

        Player pb1 = new Player("P5", "Jinal", "Shah");
        Player pb2 = new Player("P4", "Vihu", "Shah");
        Player pb3 = new Player("P5", "BlaBla", "Shah");

        //create teams
        List<Player> teamAPlayers = new LinkedList<>();
        List<Player> teamBPlayers = new LinkedList<>();
        teamAPlayers.add(pa1);
        teamAPlayers.add(pa2);
        teamAPlayers.add(pa3);

        teamBPlayers.add(pb1);
        teamBPlayers.add(pb2);
        teamBPlayers.add(pb3);

        List<String> battingOrderIds = teamAPlayers.stream().map(Player::getPlayerId).collect(Collectors.toList());
        List<String> bowlingOrderIds = teamBPlayers.stream().map(Player::getPlayerId).collect(Collectors.toList());


        Team teamA = new Team("team-a", teamAPlayers);
        Team teamB = new Team("team-b", teamAPlayers);

        //create match
        Match match = new CustomFormatCricketMatch("teamA_B", teamA, teamB, new Date(), null, 2, 1, 3);

        ScoreBoard firstInning = new InMemoryScoreBoard("teamA", "teamB", battingOrderIds, bowlingOrderIds);
        firstInning.update(new Ball("4"));
        firstInning.update(new Ball("6"));
        firstInning.update(new Ball("1"));
        firstInning.update(new Ball("WB"));

        System.out.println(firstInning.getSnapShotOfCompletedOvers());
        firstInning.update(new Ball("NB"));
        firstInning.update(new Ball("HW"));
        firstInning.update(new Ball("4F"));
        firstInning.update(new Ball("0"));
        firstInning.update(new Ball("0"));
        firstInning.update(new Ball("0"));
        firstInning.update(new Ball("0"));

        System.out.println(firstInning.getSnapShotOfCompletedOvers());
    }
}
