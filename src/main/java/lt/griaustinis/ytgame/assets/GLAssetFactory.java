package lt.griaustinis.ytgame.assets;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_CLAMP_TO_BORDER;
import static org.lwjgl.stb.STBImage.*;

public class GLAssetFactory implements AssetFactory{
    private Map<TextureKey, Texture> textures = new HashMap<>();

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

        textures.put(key, texture);
    }

    private void deleteTexture(Texture texture){
        glDeleteTextures(texture.getId());
    }

    @Override
    public Texture getTexture(TextureKey key) {
        return textures.get(key);
    }

    private String getResourcePath(String path){
        return getClass().getClassLoader().getResource(path).getPath();
    }

    @Override
    public void init() {
        loadTexture(TextureKey.CHARACTER_STANDING_1, getResourcePath("textures/character_standing_1.png"));
    }

    @Override
    public void cleanup() {
        for(Texture texture : textures.values()){
            deleteTexture(texture);
        }
    }
}
