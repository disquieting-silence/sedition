package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.core.Game;
import dsq.sedition.core.Player;
import dsq.sedition.sprite.DefaultColour;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class SpeedSprite implements Sprite {

    private static final int NUM_BARS = 10;
    private final int [] textures = new int[2];
    private final ViewPanel view;
    private final Player player;

    public SpeedSprite(Player player) {
        this.player = player;
        view = new DefaultViewPanel(NUM_BARS, new DefaultColour(1, 1, 1, 1), 0, 0);
    }

    @Override
    public void use(final GL10 g) {
        final int[] tex = new int[NUM_BARS];
        final float percent = player.getSpeed() / Player.MAX_SPEED;
        final int num = (int)(percent * NUM_BARS);
        for (int i = 0; i < tex.length; i++) {
            tex[i] = i < num ? textures[0] : textures[1];
        }
        view.draw(g, tex);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(textures.length, textures, 0);
        Textures.load(g, context, textures[0], R.drawable.glob);
        Textures.load(g, context, textures[1], R.drawable.black);
    }
}
