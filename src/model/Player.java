package model;

public final class Player {
    private final String playerId;
    private final String firstName;
    private final String lastName;

    public Player(String playerId, String firstName, String lastName) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player(String playerId) {
        this.playerId = playerId;
        this.firstName = null;
        this.lastName = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return playerId == player.playerId;

    }

    @Override
    public int hashCode() {
        return playerId.hashCode();
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
