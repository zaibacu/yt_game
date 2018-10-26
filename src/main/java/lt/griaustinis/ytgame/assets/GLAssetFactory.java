package lt.griaustinis.ytgame.assets;

import lt.griaustinis.ytgame.utils.CircularList;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.stb.STBImage.*;

public class GLAssetFactory implements AssetFactory{
    private Map<TextureKey, Texture> textures = new HashMap<>();
    private Map<AnimationKey, Animation> animations = new HashMap<>();

    private void loadTexture(TextureKey key, String path){
        ByteBuffer image;

        int width, height, id;

        try(MemoryStack stack = MemoryStack.stackPush()){
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            stbi_set_flip_vertically_on_load(true);

            image = stbi_load(path, w, h, comp, 4);

            if(image == null){
                throw new RuntimeException(String.format("Failed to load texture '%s', reason: %s", path, stbi_failure_reason()));
            }

            width = w.get();
            height = h.get();
        }

        id = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_BORDER);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_BORDER);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

        Texture texture = new Texture(id);
        float ratio = (float)width/height;
        texture.setRatio(ratio);

        textures.put(key, texture);
    }

    private void deleteTexture(Texture texture){
        glDeleteTextures(texture.getId());
    }

    @Override
    public Texture getTexture(TextureKey key) {
        return textures.get(key);
    }

    @Override
    public Animation getAnimation(AnimationKey key) {
        return animations.get(key);
    }

    private String getResourcePath(String path){
        return getClass().getClassLoader().getResource(path).getPath();
    }

    @Override
    public void init() {
        loadTexture(TextureKey.CHARACTER_STANDING_1, getResourcePath("textures/character_standing_1.png"));

        loadTexture(TextureKey.CHARACTER_WALKING_1, getResourcePath("textures/character_walking_1.png"));
        loadTexture(TextureKey.CHARACTER_WALKING_2, getResourcePath("textures/character_walking_2.png"));
        loadTexture(TextureKey.CHARACTER_WALKING_3, getResourcePath("textures/character_walking_3.png"));
        loadTexture(TextureKey.CHARACTER_WALKING_4, getResourcePath("textures/character_walking_4.png"));
        loadTexture(TextureKey.CHARACTER_WALKING_5, getResourcePath("textures/character_walking_5.png"));
        loadTexture(TextureKey.CHARACTER_WALKING_6, getResourcePath("textures/character_walking_6.png"));


        Animation characterWalkingAnimation = new Animation(new CircularList<>(Arrays.asList(
                getTexture(TextureKey.CHARACTER_WALKING_1),
                getTexture(TextureKey.CHARACTER_WALKING_2),
                getTexture(TextureKey.CHARACTER_WALKING_3),
                getTexture(TextureKey.CHARACTER_WALKING_4),
                getTexture(TextureKey.CHARACTER_WALKING_5),
                getTexture(TextureKey.CHARACTER_WALKING_6)
        )),
                0.1f
        );

        animations.put(AnimationKey.CHARACTER_WALKING, characterWalkingAnimation);
    }

    @Override
    public void cleanup() {
        for(Texture texture : textures.values()){
            deleteTexture(texture);
        }
    }
}
