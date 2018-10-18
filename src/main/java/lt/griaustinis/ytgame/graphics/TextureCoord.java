package lt.griaustinis.ytgame.graphics;

public class TextureCoord {
    private final float u;
    private final float v;

    public TextureCoord(float u, float v){
        this.u = u;
        this.v = v;
    }

    public float getU(){
        return u;
    }

    public float getV(){
        return v;
    }
}
