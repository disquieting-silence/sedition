package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.sprite.DefaultColour;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.timer.Timer;
import dsq.sedition.util.Textures;
import dsq.sedition.view.DefaultViewPanel;
import dsq.sedition.view.ViewPanel;

import javax.microedition.khronos.opengles.GL10;

public class CountdownSprite implements Sprite {
    
    private final Timer timer;
    
    private final int [] symTextures = new int[1];
    private final int [] numTextures = new int[10];

    private final ViewPanel view;

    public CountdownSprite(final Timer timer) {
        this.timer = timer;
        view = new DefaultViewPanel(5, new DefaultColour(1.0f, 1.0f, 1.0f, 1.0f), 0.4f, 0.2f);
    }
    
    private int id(int number) {
        return number <= 9 && number >= 0 ? numTextures[number] : numTextures[9];    
    }

    @Override
    public void draw(final GL10 g) {
        final int minutes = timer.getMinutes();
        final int seconds = timer.getSeconds();

        final int [] textures = {
            id(minutes / 10),
            id(minutes % 10),
            symTextures[0],
            id(seconds / 10),
            id(seconds % 10)
        };

        view.draw(g, textures);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(1, symTextures, 0);
        g.glGenTextures(numTextures.length, numTextures, 0);
        
        Textures.load(g, context, symTextures[0], R.drawable.colon);
        Textures.load(g, context, numTextures[0], R.drawable.zero);
        Textures.load(g, context, numTextures[1], R.drawable.one);
        Textures.load(g, context, numTextures[2], R.drawable.two);
        Textures.load(g, context, numTextures[3], R.drawable.three);
        Textures.load(g, context, numTextures[4], R.drawable.four);
        Textures.load(g, context, numTextures[5], R.drawable.five);
        Textures.load(g, context, numTextures[6], R.drawable.six);
        Textures.load(g, context, numTextures[7], R.drawable.seven);
        Textures.load(g, context, numTextures[8], R.drawable.eight);
        Textures.load(g, context, numTextures[9], R.drawable.nine);
    }
}
