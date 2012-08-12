package dsq.sedition.timer;

import dsq.sedition.event.EventListener;

public interface MutableTimer extends Timer {
    void update();
    void start(final long countdown);
}
