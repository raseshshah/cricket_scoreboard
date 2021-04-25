package utils;

import model.*;
import model.stats.BatsmanStats;
import model.stats.BowlerStats;
import model.stats.ScoreBoardSnapShot;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryScoreBoard implements ScoreBoard {
    private final String battingTeam, bowlingTeam;
    private final List<String> battingOrder, bowlingOrder;

    private LinkedHashMap<String, BatsmanStats> battingStats;
    private LinkedHashMap<String, BowlerStats> bowlingStats;
    private ArrayList<Over> overs;

    volatile private String playingBatsman, otherBatsman;
    volatile private int totalScored, totalWickets, totalExtra;
    volatile private int batsManIndex = -1;
    volatile private int bowlerIndex = -1;

    // possibility could keep last n over snapshot
    private AtomicReference<ScoreBoardSnapShot> snapShotOfCompletedOvers;

    public InMemoryScoreBoard(String battingTeam, String bowlingTeam, List<String> battingOrder, List<String> bowlingOrder) {
        this.bowlingTeam = bowlingTeam;
        this.battingTeam = battingTeam;
        this.battingOrder = battingOrder;
        this.bowlingOrder = bowlingOrder;
        battingStats = new LinkedHashMap<>();
        bowlingStats = new LinkedHashMap<>();
        battingOrder.forEach((p) -> battingStats.put(p, new BatsmanStats(p, false)));
        bowlingOrder.forEach((p) -> bowlingStats.put(p, new BowlerStats(p)));
        playingBatsman = nextBatsMan();
        otherBatsman = nextBatsMan();
        overs = new ArrayList<>();
        overs.add(new Over(nextBowler()));
        snapShotOfCompletedOvers = new AtomicReference<>();
        takeSnapShot();
    }

    private String nextBatsMan() {
        batsManIndex++;
        if (batsManIndex < battingOrder.size()) {
            String batsman = battingOrder.get(batsManIndex);
            battingStats.computeIfPresent(batsman, (k, v) -> new BatsmanStats(batsman, true));
            return batsman;
        } else return null;
    }

    private String nextBowler() {
        bowlerIndex++;
        return bowlingOrder.get(bowlerIndex % bowlingOrder.size());
    }

    private void swapPlayer() {
        if (otherBatsman != null) {
            String temp = playingBatsman;
            playingBatsman = otherBatsman;
            otherBatsman = temp;
        }
    }

    private void updatePlayerPosition(Ball ball) {
        if (ball.getWicket() != null) {
            if (ball.getWicket().isStrikerOut()) playingBatsman = nextBatsMan();
            else otherBatsman = nextBatsMan();
        } else {
            if (ball.getScoredRuns().type() == Runs.Type.RUNNED && ball.getScoredRuns().get() % 2 != 0) swapPlayer();
        }
    }

    private void updateOver(Ball ball) {
        Over currentOver = getCurrentOver();
        currentOver.addBall(ball);
        if (currentOver.isCompleted()) {
            swapPlayer();
            bowlingStats.computeIfPresent(currentOver.getBolwerId(), (k, v) -> v.update(currentOver));
            overs.add(new Over(nextBowler()));
            takeSnapShot();
        }
    }

    private void updateTotal(Ball ball) {
        totalScored += ball.getTotalRuns();
        totalExtra += ball.getExtraRuns();
        if (ball.getWicket() != null) totalWickets++;
    }

    private void updateStats(Ball ball) {
        String currentBolwer = getCurrentOver().getBolwerId();
        bowlingStats.computeIfPresent(currentBolwer, (k, v) -> v.update(ball));
        battingStats.computeIfPresent(playingBatsman, (k, v) -> v.update(ball));
    }

    private Over getCurrentOver() {
        return overs.get(overs.size() - 1);
    }

    @Override
    public void update(Ball ball) {
        updateStats(ball);
        updatePlayerPosition(ball);
        updateTotal(ball);
        updateOver(ball);
    }

    private int totalValidBalls() {
        return (overs.size() - 1) * Over.NO_BALLS + (Over.NO_BALLS - getCurrentOver().getRemaingBalls());
    }

    private void takeSnapShot() {
        snapShotOfCompletedOvers.set(new ScoreBoardSnapShot(battingTeam, bowlingTeam, totalScored, totalValidBalls(), totalWickets, totalExtra, new ArrayList<>(battingStats.values()), new ArrayList<>(bowlingStats.values())));
    }

    public ScoreBoardSnapShot getSnapShotOfCompletedOvers() {
        return snapShotOfCompletedOvers.get();
    }

    public ScoreBoardSnapShot latestSnapShot() {
        takeSnapShot();
        return snapShotOfCompletedOvers.get();
    }
}
