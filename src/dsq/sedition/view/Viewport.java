package dsq.sedition.view;

import dsq.sedition.scene.SceneDraw;
import dsq.sedition.sprite.Colour;

import javax.microedition.khronos.opengles.GL10;

public interface Viewport {
    void setBackground(GL10 g, float red, float green, float blue, float alpha);
    void draw(GL10 g, Colour bg, SceneDraw d);
}
