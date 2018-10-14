package lt.griaustinis.ytgame.graphics;

import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class Sprite implements Drawable{
    private final List<Vertex> vertices;
    private final float x;
    private final float y;

    public Sprite(float x, float y){
        this.vertices = Arrays.asList(
            new Vertex(-0.5f, -0.5f),
            new Vertex(0.5f, -0.5f),
            new Vertex(0.5f, 0.5f),
            new Vertex(-0.5f, 0.5f)
        );
        this.x = x;
        this.y = y;
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
}