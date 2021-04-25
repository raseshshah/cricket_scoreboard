package model.stats;


import java.util.List;

public class ScoreBoardSnapShot {
    private final String battingTeam, bowlingTeam;
    private final int totalScored, totalExtra, totalValidBalls, totalWickets;
    private final List<BatsmanStats> battingStats;
    private final List<BowlerStats> bowlingStats;

    public ScoreBoardSnapShot(String battingTeam, String bowlingTeam, int totalScored, int totalValidBalls, int totalWickets, int totalExtra, List<BatsmanStats> battingStats, List<BowlerStats> bowlingStats) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.totalScored = totalScored;
        this.totalValidBalls = totalValidBalls;
        this.totalExtra = totalExtra;
        this.totalWickets = totalWickets;
        this.battingStats = battingStats;
        this.bowlingStats = bowlingStats;
    }

    public int getTotalScored() {
        return totalScored;
    }

    public int getTotalWickets() {
        return totalWickets;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public String totalOvers() {
        String og = totalValidBalls % 6 == 0 ? "" : "." + totalValidBalls % 6;
        return (totalValidBalls / 6) + "" + og;
    }

    public boolean isAllOut() {
        return battingStats.size() - 1 == totalWickets;
    }


    public String prettyPrint() {
        StringBuffer str = new StringBuffer();
        str.append("Scorecard for " + battingTeam + ":\n");
        str.append(BatsmanStats.HEADER + "\n");
        str.append("------------------------------------------------------\n");
        battingStats.forEach((s) -> str.append(s.toString() + "\n"));

        str.append("\nScorecard for " + bowlingTeam + ":\n");
        str.append(BowlerStats.HEADER + "\n");
        str.append("-------------------------------------------------------\n");
        bowlingStats.forEach((s) -> str.append(s.toString() + "\n"));
        str.append("\n\nTotal runs: " + totalScored + "/" + totalWickets + "\n");
        str.append("Extras: " + totalExtra + "\n");
        str.append("Overs: " + totalOvers() + "\n");
        str.append("\n\n\n\n\n");
        return str.toString();
    }

    @Override
    public String toString() {
        return prettyPrint();
    }
}
