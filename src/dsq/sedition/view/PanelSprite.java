package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.sprite.Colour;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class PanelSprite implements Sprite {
    
    private final int [] textureRes;
    private final int [] textures;
    private final ViewPanel panel;

    public PanelSprite(final int[] textureRes, final Colour colour, final float hPad, final float vPad) {
        this.textureRes = textureRes;
        textures = new int[textureRes.length];
        panel = new DefaultViewPanel(textures.length, colour, hPad, vPad);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(textures.length, textures, 0);
        for (int i = 0; i < textureRes.length; i++) {
            Textures.load(g, context, textures[i], textureRes[i]);            
        }
    }

    @Override
    public void use(final GL10 g) {
        panel.draw(g, textures);
    }
}