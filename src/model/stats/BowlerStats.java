package model.stats;

import model.Ball;
import model.Over;
import model.Runs;

public class BowlerStats extends PlayerStats {
    private final int wickets, givenRuns, extras, overs, dots, maidenOvers;

    public BowlerStats(String playerId) {
        super(playerId);
        this.wickets = 0;
        this.givenRuns = 0;
        this.extras = 0;
        this.overs = 0;
        this.dots = 0;
        this.maidenOvers = 0;
    }

    public BowlerStats(String playerId, int wickets, int givenRuns, int extras, int overs, int dots, int maidenOvers) {
        super(playerId);
        this.wickets = wickets;
        this.givenRuns = givenRuns;
        this.extras = extras;
        this.overs = overs;
        this.dots = dots;
        this.maidenOvers = maidenOvers;
    }

    @Override
    public BowlerStats update(Ball ball) {
        int extras = this.extras + ball.getExtraRuns();
        int givenRuns = ball.getScoredRuns().type() != Runs.Type.BYE ? this.givenRuns + ball.getScoredRuns().get() : this.givenRuns;
        int wickets = ball.getWicket() != null ? this.wickets + 1 : this.wickets;
        int dots = ball.isDot() ? this.dots + 1 : 0;
        return new BowlerStats(playerId, wickets, givenRuns, extras, overs, dots, maidenOvers);
    }

    @Override
    public BowlerStats update(Over over) {
        int maidenOvers = over.isMaiden() ? this.maidenOvers + 1 : this.maidenOvers;
        return new BowlerStats(playerId, wickets, givenRuns, extras, overs + 1, dots, maidenOvers);
    }

    public static String HEADER = "Name" + strSep + "Wickets" + strSep + "Runs" + strSep + "Overs" + strSep + "Dots" + strSep + "Maiden" + strSep + "Average";

    @Override
    public String toString() {
        return playerId + strSep + strSep + wickets + strSep + strSep + givenRuns + strSep + strSep + overs + strSep + strSep + dots + strSep + strSep + maidenOvers + strSep + strSep + getAverage();
    }

    public double getAverage() {
        return wickets == 0 ? 0 : (givenRuns + extras) / wickets;
    }
}

