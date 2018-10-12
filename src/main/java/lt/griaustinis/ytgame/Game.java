package lt.griaustinis.ytgame;

import lt.griaustinis.ytgame.core.Command;
import lt.griaustinis.ytgame.core.GameControls;
import lt.griaustinis.ytgame.core.GameWindow;
import lt.griaustinis.ytgame.graphics.GLRenderer;
import lt.griaustinis.ytgame.graphics.Renderer;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Game {
    private final GameWindow window;
    private final GameControls controls;
    private final Renderer renderer;

    public Game(){
        this.window = new GameWindow();
        this.controls = new GameControls(this.window);
        this.renderer = new GLRenderer();
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

    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();


        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window.getId()) ) {
            renderer.clearScreen();
            renderer.render();

            glfwSwapBuffers(window.getId()); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    private void cleanup(){
        window.cleanup();
        controls.cleanup();
    }

    public static void main(String[] args) {
        new Game().run();
    }

}