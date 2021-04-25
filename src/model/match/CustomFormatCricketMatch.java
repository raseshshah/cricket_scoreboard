package model.match;

import model.Stadium;
import model.Team;

import java.util.Date;

public final class CustomFormatCricketMatch extends Match implements CricketMatchFormat {
    volatile private int overs, days, numberOfPlayerInATeam;

    public CustomFormatCricketMatch() {
    }

    public CustomFormatCricketMatch(String matchId, Team homeTeam, Team awayTeam, Date date, Stadium stadium, int overs, int days, int numberOfPlayerInATeam) {
        super(matchId, homeTeam, awayTeam, date, stadium);
        this.overs = overs;
        this.days = days;
        this.numberOfPlayerInATeam = numberOfPlayerInATeam;
    }

    public int getOvers() {
        return overs;
    }

    public int getDays() {
        return days;
    }

    public int getNumberOfPlayerInATeam() {
        return numberOfPlayerInATeam;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setNumberOfPlayerInATeam(int numberOfPlayerInATeam) {
        this.numberOfPlayerInATeam = numberOfPlayerInATeam;
    }
}