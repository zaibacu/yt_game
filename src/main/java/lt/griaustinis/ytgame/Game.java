package lt.griaustinis.ytgame;

import lt.griaustinis.ytgame.assets.AssetFactory;
import lt.griaustinis.ytgame.assets.GLAssetFactory;
import lt.griaustinis.ytgame.assets.TextureKey;
import lt.griaustinis.ytgame.core.Command;
import lt.griaustinis.ytgame.core.GameControls;
import lt.griaustinis.ytgame.core.GameWindow;
import lt.griaustinis.ytgame.graphics.Drawable;
import lt.griaustinis.ytgame.graphics.GLRenderer;
import lt.griaustinis.ytgame.graphics.Renderer;
import lt.griaustinis.ytgame.graphics.Sprite;
import org.lwjgl.*;
import org.lwjgl.glfw.*;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Game {
    private final GameWindow window;
    private final GameControls controls;
    private final Renderer renderer;
    private final List<Drawable> gameObjects = new ArrayList<>();
    private final AssetFactory assetFactory;

    public Game(){
        this.window = new GameWindow();
        this.controls = new GameControls(this.window);
        this.renderer = new GLRenderer();
        this.assetFactory = new GLAssetFactory();
    }


    public void run() {
        System.out.println("LWJGL " + Version.getVersion() + "!");

        init();
        loop();
        cleanup();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        window.init();
        controls.init();

        controls.registerEvent(GLFW_KEY_ESCAPE, GameControls.ActionTypes.ON_RELEASE, new Command() {
            @Override
            public void execute() {
                glfwSetWindowShouldClose(window.getId(), true);
            }
        });

        renderer.init();

        assetFactory.init();
        this.gameObjects.add(new Sprite(assetFactory.getTexture(TextureKey.CHARACTER_STANDING_1), -0.5f, -0.5f));
    }

    private void loop() {
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window.getId()) ) {
            renderer.clearScreen();
            for(Drawable drawObj : gameObjects){
                renderer.render(drawObj);
            }

            glfwSwapBuffers(window.getId()); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    private void cleanup(){
        window.cleanup();
        controls.cleanup();
        renderer.cleanup();
        assetFactory.cleanup();
    }

    public static void main(String[] args) {
        new Game().run();
    }

}