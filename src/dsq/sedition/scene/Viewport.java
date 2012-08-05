package dsq.sedition.scene;

import javax.microedition.khronos.opengles.GL10;

public interface Viewport {
    void setBackground(GL10 g, float red, float green, float blue, float alpha);
    void setActive(GL10 g);
}
