package model;

public final class Stadium {
    private final int id;
    private final String name;
    private final Location location;
    private final int capacity;

    public Stadium(int id, String name, Location location, int capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Location getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stadium stadium = (Stadium) o;

        return id == stadium.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
