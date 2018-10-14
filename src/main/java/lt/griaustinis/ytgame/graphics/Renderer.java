package lt.griaustinis.ytgame.graphics;

import lt.griaustinis.ytgame.core.GameResource;

public interface Renderer extends GameResource {
    void render(Drawable drawObj);
    void clearScreen();
}
