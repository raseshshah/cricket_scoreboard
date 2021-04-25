package model.stats;

import model.Team;
import model.match.Match;

public final class MatchResult {


    public enum Result {
        WON_BY_WICKET, WON_BY_RUNS, DRAW, ONGOING;
    }

    private Team winningTeam;

    private Result result;

    private int runsOrwickets;

    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public MatchResult() {
        result = Result.ONGOING;
    }

    public Team getWinningTeam() {
        return winningTeam;
    }

    public Result getResult() {
        return result;
    }

    public void setRunsOrwickets(int runsOrwickets) {
        this.runsOrwickets = runsOrwickets;
    }

    @Override
    public String toString() {
        String str = "Result: ";
        switch (result) {
            case WON_BY_RUNS:
                str += winningTeam.getName() + " won the match by " + runsOrwickets + " runs";
                break;
            case WON_BY_WICKET:
                str += winningTeam.getName() + "won the match by" + runsOrwickets + " wickets";
                break;
            case DRAW:
                str += "Excited match end in draw";
                break;
            case ONGOING:
                str += "hold your nerve match is still on going";
                break;
        }
        return str;
    }
}
