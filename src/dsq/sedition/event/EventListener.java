package dsq.sedition.event;

import dsq.sedition.core.Player;

public interface EventListener {

    void startGame();
    void collision(Player player);
    void stop(Player player);
    void moving(Player player);

    void init();

    void timeExpired();
}
