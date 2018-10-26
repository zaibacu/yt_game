package lt.griaustinis.ytgame.assets;

import lt.griaustinis.ytgame.utils.CircularList;

public class Animation {
    private final CircularList<Texture> frames;
    private Texture currentFrame;
    private final float speed;
    private float acc = 0;

    public Animation(CircularList<Texture> frames, float speed){
        this.frames = frames;
        this.currentFrame = frames.first();
        this.speed = speed;
    }

    public Texture getCurrentFrame(){
        return currentFrame;
    }

    public void update(float delta){
       acc += delta;
       if(acc > speed){
           currentFrame = frames.iterator().next();
           acc = 0;
       }
    }
}
