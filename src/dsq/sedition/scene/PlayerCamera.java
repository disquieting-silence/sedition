package dsq.sedition.scene;

import dsq.sedition.core.Player;

import javax.microedition.khronos.opengles.GL10;

public class PlayerCamera implements Camera {

    private final float PLAYER_Y = -1.5f;
    
    private final PlayerBounce bouncer = new DefaultPlayerBounce(24, 0.08f);

    private final Player player;

    public PlayerCamera(final Player player) {
        this.player = player;
    }



    @Override
    public void use(final GL10 g) {
        playerPos(g);
//        mazePos(g);
    }

    private void playerPos(final GL10 g) {
        Coordinate pos = player.getPosition();
        g.glRotatef(player.getDirection(), 0f, 1f, 0f);

        bouncer.update();
        g.glTranslatef(-pos.x, PLAYER_Y - bouncer.level(), -pos.z);
    }
    
    private void mazePos(final GL10 g) {
        final Coordinate pos = player.getPosition();
        g.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        g.glTranslatef(pos.x, -99, pos.z);
        g.glScalef(0.1f, 0.1f, 0.1f);
    }
}
