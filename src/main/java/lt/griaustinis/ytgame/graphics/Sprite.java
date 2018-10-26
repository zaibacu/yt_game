package lt.griaustinis.ytgame.graphics;

import lt.griaustinis.ytgame.assets.Animation;
import lt.griaustinis.ytgame.assets.Texture;

import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Sprite implements Drawable{
    private final List<Vertex> vertices;
    private final float x;
    private final float y;
    private final Animation animation;

    public Sprite(Animation animation, float x, float y){
        this.vertices = Arrays.asList(
            new Vertex(-0.5f, -0.5f),
            new Vertex(0.5f, -0.5f),
            new Vertex(0.5f, 0.5f),
            new Vertex(-0.5f, 0.5f)
        );
        this.x = x;
        this.y = y;
        this.animation = animation;
    }

    public List<Vertex> getVertices(){
        return vertices;
    }

    public int getDrawType(){
        return GL_QUADS;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public Texture getTexture() {
        return animation.getCurrentFrame();
    }

    @Override
    public void update(float delta) {
        animation.update(delta);
    }
}
