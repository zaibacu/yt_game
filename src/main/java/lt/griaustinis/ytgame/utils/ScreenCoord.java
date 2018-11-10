package lt.griaustinis.ytgame.utils;

public class ScreenCoord {
    private final float x;
    private final float y;
    private final float z;

    private float scaleX;
    private float scaleY;
    private float scaleZ;

    public ScreenCoord(float x, float y, float scaleX, float scaleY){
        this.x = x;
        this.y = y;
        this.z = 0f;

        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = 1f;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getZ(){
        return z;
    }

    public float getScaleX(){
        return scaleX;
    }

    public float getScaleY(){
        return scaleY;
    }

    public float getScaleZ(){
        return scaleZ;
    }
}
