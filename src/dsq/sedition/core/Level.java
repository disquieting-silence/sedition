package dsq.sedition.core;

import dsq.sedition.collision.Collidable;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

public interface Level {
    List<Sprite> sprites(final ViewState viewState);
    List<Collidable> obstacles(final ViewState viewState);

    ArrayList<Sprite> allSprites();
    Coordinate start();
}
