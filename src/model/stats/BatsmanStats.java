package model.stats;


import model.Ball;
import model.Over;
import model.Runs;

public class BatsmanStats extends PlayerStats {
    private final int runs, fours, sixs, balls;
    private final boolean isOnCrease;

    public BatsmanStats(String player, int runs, int fours, int sixs, int balls, Boolean isOnCrease) {
        super(player);
        this.runs = runs;
        this.fours = fours;
        this.sixs = sixs;
        this.balls = balls;
        this.isOnCrease = isOnCrease;
    }

    public BatsmanStats(String player, Boolean isOnCrease) {
        super(player);
        this.runs = 0;
        this.fours = 0;
        this.sixs = 0;
        this.balls = 0;
        this.isOnCrease = isOnCrease;
    }

    @Override
    public BatsmanStats update(Ball ball) {
        int fours = ball.getScoredRuns().type() == Runs.Type.FOUR ? this.fours + 1 : this.fours;
        int sixs = ball.getScoredRuns().type() == Runs.Type.SIX ? this.sixs + 1 : this.sixs;
        int runs = ball.getScoredRuns().type() != Runs.Type.BYE ? this.runs + ball.getScoredRuns().get() : this.runs;
        int balls = ball.getType() == Ball.Type.VALID ? this.balls + 1 : this.balls;
        return new BatsmanStats(playerId, runs, fours, sixs, balls, ball.getWicket() == null);
    }

    public BatsmanStats update(Over over) {
        return this;
    }

    public double getStrikeRate() {
        return balls == 0 ? 0 : ((double) runs / balls) * 100;
    }

    public int getRuns() {
        return runs;
    }

    public int getNumberOfFours() {
        return fours;
    }

    public int getNumberOfSixs() {
        return sixs;
    }

    public int getNumberOfBallsFaced() {
        return balls;
    }

    public static String HEADER = "Name" + strSep + "Scores" + strSep + "4s" + strSep + strSep + "6s" + strSep + strSep + "Balls" + strSep + "Strike Rate";

    @Override
    public String toString() {
        String batting = isOnCrease ? "*" : "";
        return playerId + batting + strSep + strSep + runs + strSep + strSep + fours + strSep + strSep + sixs + strSep + strSep + balls + strSep + strSep + (int) getStrikeRate() + "%";
    }
}