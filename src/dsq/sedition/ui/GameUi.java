package dsq.sedition.ui;

import android.view.View;
import dsq.sedition.core.Game;
import dsq.sedition.gl.GameViewer;

public interface GameUi {
    View.OnTouchListener onTouch(final Game game, final GameViewer viewer);
}
