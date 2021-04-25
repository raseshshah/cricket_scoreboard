package controller;

import events.*;
import model.*;
import model.match.CustomFormatCricketMatch;
import model.match.Match;
import model.stats.MatchResult;
import model.stats.ScoreBoardSnapShot;
import utils.InMemoryScoreBoard;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMatchController implements MatchController {
    public static InMemoryMatchController instance = new InMemoryMatchController();
    private ConcurrentHashMap<String, Match> matches;
    private ConcurrentHashMap<String, Team> teams;

    private InMemoryMatchController() {
        matches = new ConcurrentHashMap<>();
        teams = new ConcurrentHashMap<>();
    }

    @Override
    public void handleEvent(BaseEvent event) {
        if (event instanceof CreateTeamEvent) {
            handleCreateTeamEvent((CreateTeamEvent) event);
        } else if (event instanceof StartMatchEvent) {
            handleStartMatchEvent(((StartMatchEvent) event));
        } else if (event instanceof MatchEndEvent) {
            handleMatchEndEvent(((MatchEndEvent) event));
        } else if (event instanceof BallEvent) {
            handleBallEvent(((BallEvent) event));
        } else if (event instanceof StartNewInning) {
            handleStartNewInning(((StartNewInning) event));
        } else if (event instanceof StartNewOverEvent) {
            handleStartOverEvent(((StartNewOverEvent) event));
        } else {
            throw new IllegalArgumentException("unknown event");
        }
    }

    private void handleCreateTeamEvent(CreateTeamEvent team) {
        teams.computeIfAbsent(team.getTeamName(), (key) -> {
            List<Player> players = new LinkedList<Player>();
            for (String id : team.getPlayerIds()) {
                players.add(new Player(id));
            }
            return new Team(team.getTeamName(), players);
        });
    }

    private void handleStartMatchEvent(StartMatchEvent event) {
        matches.computeIfAbsent(event.getId(), (key) -> {
            CustomFormatCricketMatch match = new CustomFormatCricketMatch();
            if (!teams.containsKey(event.getHomeTeamId()) || !teams.containsKey(event.getAwayTeamId())) {
                throw new IllegalArgumentException("team is undefined");
            }
            Team home = teams.get(event.getHomeTeamId()), away = teams.get(event.getAwayTeamId());
            if (home.getPlayers().size() != away.getPlayers().size()) {
                throw new IllegalArgumentException("team should have same number of players");
            }
            match.setHomeTeam(home);
            match.setAwayTeam(away);
            match.setDays(1);
            match.setOvers(event.getOvers());
            match.setNumberOfPlayerInATeam(home.getPlayers().size());
            return match;
        });
    }

    private void handleBallEvent(BallEvent event) {
        matches.get(event.getMatchId()).getCurrent().update(new Ball(event.getValue()));
    }

    private void handleStartNewInning(StartNewInning event) {
        Match match = matches.get(event.getMatchId());
        if (match.getCurrent() == null) {
            match.setFirstInning(new InMemoryScoreBoard(event.getBattingTeam(), event.getBowlingTeam(), event.getBattingOrder(), event.getBowlingOrder()));
            match.setCurrent(match.getFirstInning());
        } else {
            printSnapShot(match);
            match.setSecondInning(new InMemoryScoreBoard(event.getBattingTeam(), event.getBowlingTeam(), event.getBattingOrder(), event.getBowlingOrder()));
            match.setCurrent(match.getSecondInning());
        }
    }

    private void handleStartOverEvent(StartNewOverEvent event) {
        Match match = matches.get(event.getMatchId());
        printSnapShot(match);
    }

    private void handleMatchEndEvent(MatchEndEvent event) {
        Match match = matches.get(event.getMatchId());
        ScoreBoardSnapShot sa = match.getFirstInning().latestSnapShot();
        ScoreBoardSnapShot sb = match.getSecondInning().latestSnapShot();
        printSnapShot(match);
        int teamAScore = sa.getTotalScored();
        int teamBScore = sb.getTotalScored();
        MatchResult result = match.getMatchResult();
        if (teamAScore > teamBScore) {
            result.setResult(MatchResult.Result.WON_BY_RUNS);
            result.setWinningTeam(teams.get(sa.getBattingTeam()));
            result.setRunsOrwickets(teamAScore - teamBScore);
        } else if (teamAScore == teamBScore) {
            result.setResult(MatchResult.Result.DRAW);
        } else {
            result.setResult(MatchResult.Result.WON_BY_WICKET);
            result.setRunsOrwickets(match.getAwayTeam().getPlayers().size() - sb.getTotalWickets());
            result.setWinningTeam(teams.get(sa.getBattingTeam()));
        }
        System.out.print(result);
    }

    private void printSnapShot(Match match) {
        System.out.print(match.getCurrent().getSnapShotOfCompletedOvers().prettyPrint());
    }

    @Override
    public MatchController handle(BaseEvent event) {
        handleEvent(event);
        return this;
    }
}
