package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.timer.Timer;
import dsq.sedition.util.Panels;
import dsq.sedition.util.Quads;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;
import java.util.List;

public class CommandsSprite implements Sprite {

    private final int [] textures = new int[3];

    private final List<Quad> sprites;

    public CommandsSprite() {
        sprites = Panels.make(3, 0f, 0f, 3f, 1f, new DefaultColour(1.0f, 1.0f, 1.0f, 1.0f));
    }


    @Override
    public void draw(final GL10 g) {
        for (int i = 0; i < sprites.size(); i++) {
            Quads.draw(g, sprites.get(i), textures[i]);
        }
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(textures.length, textures, 0);
        
        Textures.load(g, context, textures[0], R.drawable.back);
        Textures.load(g, context, textures[1], R.drawable.back);
        Textures.load(g, context, textures[2], R.drawable.back);
    }
}
