package dsq.sedition.light;

import javax.microedition.khronos.opengles.GL10;

public interface Light {
    void display(GL10 g);
    void hide(GL10 g);
}
