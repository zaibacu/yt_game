package lt.griaustinis.ytgame.graphics;

import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

public class GLRenderer implements Renderer{
    @Override
    public void render(Drawable drawObj) {
        glColor3f(0.0f, 0.5f, 0.5f);
        glPushMatrix();
        glTranslatef(drawObj.getX(), drawObj.getY(), 1.0f);
        //glRotatef(45f, 0.0f, 0.0f, 1.0f);
        //glScalef(0.5f, 0.5f, 1.0f);
        glBegin(drawObj.getDrawType());
            for(Vertex v : drawObj.getVertices()){
                glVertex2f(v.getX(), v.getY());
            }
        glEnd();
        glPopMatrix();
    }

    @Override
    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }

    @Override
    public void init() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    @Override
    public void cleanup() {

    }
}
