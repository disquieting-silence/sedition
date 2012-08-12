package dsq.sedition.timer;

import dsq.sedition.event.EventListener;

public class DefaultMutableTimer implements MutableTimer {
    
    private long target;
    private long counter;
    private final EventListener events;

    public DefaultMutableTimer(final EventListener events) {
        this.events = events;
    }

    @Override
    public void update() {
        counter = target - System.currentTimeMillis();
        if (counter <= 0) events.timeExpired();
    }

    @Override
    public void start(final long countdown) {
        target = System.currentTimeMillis() + countdown;          
    }

    @Override
    public int getMinutes() {
        return counter > 0 ? (int)(counter / 60000L) : 0;
    }

    @Override
    public int getSeconds() {
        return counter > 0 ? (int)(counter % 60000L) / 1000 : 0;
    }
}
