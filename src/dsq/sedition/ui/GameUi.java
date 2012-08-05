package dsq.sedition.ui;

import android.view.View;
import dsq.sedition.core.Game;

public interface GameUi {
    View.OnTouchListener onTouch(final Game game);
}
