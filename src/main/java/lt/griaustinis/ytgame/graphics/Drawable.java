package lt.griaustinis.ytgame.graphics;

import lt.griaustinis.ytgame.assets.Texture;

import java.util.List;

public interface Drawable {
    List<Vertex> getVertices();
    int getDrawType();

    float getX();
    float getY();

    Texture getTexture();

    void update(float delta);
}
