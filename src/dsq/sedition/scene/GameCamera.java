package dsq.sedition.scene;

import dsq.sedition.core.Player;

import javax.microedition.khronos.opengles.GL10;

public class GameCamera implements GlimpseCamera {
    
    private final Camera playerCam;
    private final ScaledCamera scaleCam;
    
    private Camera current;

    public GameCamera(final Player player) {
        playerCam = new PlayerCamera(player);
        scaleCam = new EagleCamera(player.getPosition());
        current = scaleCam;
    }

    @Override
    public void position(final GL10 g) {
        current.position(g);
    }

    @Override
    public void transition() {
        current = playerCam;
    }

    @Override
    public void setScale(final float scale) {
        scaleCam.setScale(scale);
    }

    @Override
    public float getScale() {
        return scaleCam.getScale();
    }
}
