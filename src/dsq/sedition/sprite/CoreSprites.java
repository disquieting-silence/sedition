package dsq.sedition.sprite;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public interface CoreSprites {
    
    void load(GL10 g, Context context);
    int wallTexture();
    int roofTexture();
}
