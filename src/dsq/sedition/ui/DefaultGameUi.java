package dsq.sedition.ui;

import android.view.MotionEvent;
import android.view.View;
import dsq.sedition.core.Game;
import dsq.sedition.gl.GameViewer;
import dsq.sedition.view.GameView;

public class DefaultGameUi implements GameUi {

    @Override
    public View.OnTouchListener onTouch(final Game game, final GameViewer viewer) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                final boolean down = motionEvent.getAction() == MotionEvent.ACTION_DOWN;
                final boolean up = motionEvent.getAction() == MotionEvent.ACTION_UP;
                final boolean move = motionEvent.getAction() == MotionEvent.ACTION_MOVE;

                final float percentX = motionEvent.getX() / view.getWidth();
                final float percentY = motionEvent.getY() / view.getHeight();

                final GameView gameView = viewer.getView();
                if (up) {
                    gameView.offTouch(game);
                } else if (down) {
                    gameView.onTouch(game, percentX, percentY);
                } else if (move) {
                    gameView.moveTouch(game, percentX, percentY);
                }

                return true;
            }
        };
    }
}
