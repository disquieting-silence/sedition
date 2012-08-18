package dsq.sedition.ui;

import android.view.MotionEvent;
import android.view.View;
import dsq.sedition.core.Game;
import dsq.sedition.gl.GameViewer;
import dsq.sedition.view.Jaguar;

public class DefaultGameUi implements GameUi {

    @Override
    public View.OnTouchListener onTouch(final Game game, final GameViewer viewer) {
        return new View.OnTouchListener() {
            @Override
            // FIX 3/06/12 Magic numbers abound. Sort of viewports.
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                final boolean down = motionEvent.getAction() == MotionEvent.ACTION_DOWN;
                final boolean up = motionEvent.getAction() == MotionEvent.ACTION_UP;
                final boolean move = motionEvent.getAction() == MotionEvent.ACTION_MOVE;

                final Jaguar gameView = viewer.getView();
                if (up) {
                    gameView.offCommand(game);
                } else if (down || move) {
                    gameView.onCommand(game, motionEvent.getX() / view.getWidth(), motionEvent.getY() / view.getHeight());
                }

                return true;
            }
        };
    }
}
