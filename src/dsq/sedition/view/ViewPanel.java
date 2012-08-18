package dsq.sedition.view;

import javax.microedition.khronos.opengles.GL10;

public interface ViewPanel {
    void draw(GL10 g, int[] textures);
}
