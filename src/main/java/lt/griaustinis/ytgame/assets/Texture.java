package lt.griaustinis.ytgame.assets;

import lt.griaustinis.ytgame.graphics.TextureCoord;

import java.util.Arrays;
import java.util.List;

public class Texture {
    private final int id;
    private final List<TextureCoord> coords;
    private float ratio = 1.0f;

    public Texture(int id, List<TextureCoord> coords){
        this.id = id;
        this.coords = coords;
    }

    public Texture(int id){
        this.id = id;
        this.coords = Arrays.asList(
            new TextureCoord(0.0f, 0.0f),
            new TextureCoord(1.0f, 0.0f),
            new TextureCoord(1.0f, 1.0f),
            new TextureCoord(0.0f, 1.0f)
        );
    }

    public void setRatio(float ratio){
        this.ratio = ratio;
    }

    public float getRatio(){
        return ratio;
    }

    public int getId(){
        return id;
    }

    public List<TextureCoord> getTextureCoord(){
        return coords;
    }
}
