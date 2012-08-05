package dsq.sedition.sprite;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public interface Sprite {
    void draw(GL10 g);

    void loadGLTexture(GL10 g, Context context);
}
