package dsq.sedition.view;

import dsq.sedition.core.Game;

import javax.microedition.khronos.opengles.GL10;

public interface Jaguar {
    void onCommand(final Game game, float left, float top);
    void offCommand(final Game game);
    
    void onResize(final GL10 g, final int width, final int height, final Game game);
    
    void projection(final GL10 g, final int width, final int height, final Game game);
    void model(final GL10 g, final int width, final int height, final Game game);
}
