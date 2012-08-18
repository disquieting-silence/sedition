package dsq.sedition.view;

import dsq.sedition.sprite.Colour;

import javax.microedition.khronos.opengles.GL10;
import java.util.List;

public interface Jaguars {
    void projection(GL10 g, int width, int height, float farClip);
    
    void draw(GL10 g, Box box, List<? extends GameModel> models, final Colour bg);
    void draw(GL10 g, Box box, GameModel model, final Colour bg);
}
