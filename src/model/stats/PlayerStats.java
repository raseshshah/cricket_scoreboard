package model.stats;

import model.*;

public abstract class PlayerStats {
    final String playerId;

    protected static String strSep = "\t";

    public PlayerStats(String playerId) {
        this.playerId = playerId;
    }

    abstract PlayerStats update(Ball ball);

    abstract PlayerStats update(Over ball);
}





