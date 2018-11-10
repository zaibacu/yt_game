package lt.griaustinis.ytgame.core;

import lt.griaustinis.ytgame.utils.GameCoord;
import lt.griaustinis.ytgame.utils.ScreenCoord;

public class Camera {

    // Singleton Part
    private static Camera instance = null;

    public static Camera getInstance(){
        if(instance == null){
            instance = new Camera(0, 0);
        }

        return instance;
    }

    // Actual Camera
    private GameCoord position;
    private float zoom;

    private Camera(int x, int y){
        this.position = new GameCoord(x, y);
        this.zoom = 5f;
    }

    public ScreenCoord translate(GameCoord targetPosition){
        float x = (float)(position.getX() - targetPosition.getX()) / zoom;
        float y = (float)(position.getY() - targetPosition.getY()) / zoom;

        float scaleX = 1f / zoom;
        float scaleY = 1f / zoom;

        return new ScreenCoord(x, y, scaleX, scaleY);
    }

    public void addZoom(float z){
        this.zoom += z;
    }

}
