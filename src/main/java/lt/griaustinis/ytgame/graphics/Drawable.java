package lt.griaustinis.ytgame.graphics;

import java.util.List;

public interface Drawable {
    List<Vertex> getVertices();
    int getDrawType();

    float getX();
    float getY();
}
