package events;

public abstract class BaseEvent {
    final private long timestamp;

    public BaseEvent() {
        this.timestamp = System.currentTimeMillis();
    }
}
