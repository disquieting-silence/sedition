package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class DefaultCoreSprites implements CoreSprites {

    private static final int WALL_TEXTURE = 0;
    private static final int ROOF_TEXTURE = 1;
    
    private final int[] textures = new int[2];
    
    @Override
    public void load(final GL10 g, final Context context) {
        g.glGenTextures(2, textures, 0);
        Textures.load(g, context, textures[WALL_TEXTURE], R.drawable.glob);
        Textures.load(g, context, textures[ROOF_TEXTURE], R.drawable.glob);
    }

    @Override
    public int wallTexture() {
        return textures[WALL_TEXTURE];
    }

    @Override
    public int roofTexture() {
        return textures[ROOF_TEXTURE];
    }
}
