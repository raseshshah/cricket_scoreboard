package model.match;

import model.Stadium;
import model.Team;
import model.stats.MatchResult;
import utils.ScoreBoard;

import java.util.Date;

public abstract class Match {
    volatile private String matchId;
    volatile private Team homeTeam, awayTeam;
    volatile private Date date;
    volatile private Stadium stadium;
    volatile private ScoreBoard current, firstInning, secondInning;
    volatile private MatchResult matchResult;

    public Match(String matchId, Team homeTeam, Team awayTeam, Date date, Stadium stadium) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.stadium = stadium;
        this.matchResult = new MatchResult();
    }

    public Match() {
        this.matchResult = new MatchResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        return matchId == match.matchId;
    }

    @Override
    public int hashCode() {
        return matchId.hashCode();
    }

    public String getMatchId() {
        return matchId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public ScoreBoard getFirstInning() {
        return firstInning;
    }

    public void setFirstInning(ScoreBoard firstInning) {
        this.firstInning = firstInning;
    }

    public ScoreBoard getSecondInning() {
        return secondInning;
    }

    public void setSecondInning(ScoreBoard secondInning) {
        this.secondInning = secondInning;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public ScoreBoard getCurrent() {
        return current;
    }

    public void setCurrent(ScoreBoard current) {
        this.current = current;
    }
}
