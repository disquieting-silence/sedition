package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.view.GameModel;

import javax.microedition.khronos.opengles.GL10;

public interface Sprite extends GameModel {
    void loadGLTexture(GL10 g, Context context);
}
