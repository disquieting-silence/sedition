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
                final boolean down = motionEvent.getAction() == MotionEvent.ACTION_DOWN;
                final boolean up = motionEvent.getAction() == MotionEvent.ACTION_UP;
                final boolean move = motionEvent.getAction() == MotionEvent.ACTION_MOVE;

                // FIX 18/08/12 This 100 needs to be calculated. It can't just be 100.
                final boolean onCommand = motionEvent.getY() > (view.getHeight() - 100);
                final boolean onLeft = motionEvent.getX() < view.getWidth() * 0.33;
                final boolean onRight = motionEvent.getX() > view.getWidth() * 0.66;
                
                if (up) {
                    game.stopTurning();
                    game.slowDown();
                } 

                if (onCommand && (down || move)) {
                    if (onLeft) {
                        game.turnLeft();
                        game.slowDown();
                    } else if (onRight) {
                        game.turnRight();
                        game.slowDown();
                    } else {
                        game.speedUp();
                        game.stopTurning();
                    }
                }
                return true;
            }
        };
    }
}
