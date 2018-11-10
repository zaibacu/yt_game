package lt.griaustinis.ytgame.graphics;

import lt.griaustinis.ytgame.assets.Texture;
import lt.griaustinis.ytgame.utils.ScreenCoord;

import java.util.List;

public interface Drawable {
    List<Vertex> getVertices();
    int getDrawType();

    ScreenCoord getScreenCoord();

    Texture getTexture();

    void update(float delta);
}
