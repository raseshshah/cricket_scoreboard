package model;

import java.util.List;
import java.util.Set;

public class Team {
    private final String name;
    private final List<Player> players;

    public Team(String name, List<Player> players) {
        if (players.size() == 0) throw new IllegalArgumentException("expected at-least one player team");
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String id) {
        for (Player p : players) if (p.getPlayerId().equals(id)) return p;
        return null;
    }
}
