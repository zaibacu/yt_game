package lt.griaustinis.ytgame.assets;

import lt.griaustinis.ytgame.core.GameResource;

public interface AssetFactory extends GameResource {
    Texture getTexture(TextureKey key);
}
