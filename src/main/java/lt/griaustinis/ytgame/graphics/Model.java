package lt.griaustinis.ytgame.graphics;

import lt.griaustinis.ytgame.assets.Animation;
import lt.griaustinis.ytgame.assets.Texture;
import lt.griaustinis.ytgame.core.Camera;
import lt.griaustinis.ytgame.utils.GameCoord;
import lt.griaustinis.ytgame.utils.ScreenCoord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public abstract class Model implements Drawable{
    public enum Action{
        STANDING,
        WALKING,
        JUMPING
    }

    protected Map<Action, Animation> animations = new HashMap<>();
    protected Animation currentAnimation;

    private GameCoord position;
    private final List<Vertex> vertices;

    public Model(int x, int y){
        this.position = new GameCoord(x, y);
        this.vertices = Arrays.asList(
                new Vertex(-1f, -1f),
                new Vertex(1f, -1f),
                new Vertex(1f, 1f),
                new Vertex(-1f, 1f)
        );
    }

    public GameCoord getPosition(){
        return position;
    }

    public void updatePosition(GameCoord other){
        this.position = position.add(other);
    }

    @Override
    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public int getDrawType() {
        return GL_QUADS;
    }

    @Override
    public ScreenCoord getScreenCoord() {
        return Camera.getInstance().translate(position);
    }


    @Override
    public void update(float delta) {
        currentAnimation.update(delta);
    }

    @Override
    public Texture getTexture() {
        return currentAnimation.getCurrentFrame();
    }
}
