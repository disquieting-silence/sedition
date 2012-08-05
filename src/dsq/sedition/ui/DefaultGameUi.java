package dsq.sedition.ui;

import android.view.MotionEvent;
import android.view.View;
import dsq.sedition.core.Game;

public class DefaultGameUi implements GameUi {

    @Override
    public View.OnTouchListener onTouch(final Game game) {
        return new View.OnTouchListener() {
            @Override
            // FIX 3/06/12 Magic numbers abound. Sort of viewports.
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                boolean down = motionEvent.getAction() == MotionEvent.ACTION_DOWN;
                final boolean up = motionEvent.getAction() == MotionEvent.ACTION_UP;
                if (up) {
                    game.stopTurning();
                    game.slowDown();
                }

                if (motionEvent.getY() > (view.getHeight() - 100)) {
                    if (motionEvent.getX() < view.getWidth()  * 0.33) {
                        if (down) game.turnLeft();
                    } else if (motionEvent.getX() > view.getWidth() * 0.66) {
                        if (down) game.turnRight();
                    } else {
                        if (down) game.speedUp();
                    }
                }
                return true;
            }
        };
    }
}
