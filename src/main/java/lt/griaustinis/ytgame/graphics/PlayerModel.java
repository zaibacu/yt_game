package lt.griaustinis.ytgame.graphics;


import lt.griaustinis.ytgame.assets.AnimationKey;
import lt.griaustinis.ytgame.assets.AssetFactory;

public class PlayerModel extends Model {
    public PlayerModel(int x, int y, AssetFactory assets){
        super(x, y);

        this.animations.put(Action.WALKING, assets.getAnimation(AnimationKey.CHARACTER_WALKING));
        this.currentAnimation = this.animations.get(Action.WALKING);
    }

}
