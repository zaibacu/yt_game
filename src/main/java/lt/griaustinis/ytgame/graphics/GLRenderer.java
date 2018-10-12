package lt.griaustinis.ytgame.graphics;

import static org.lwjgl.opengl.GL11.*;

public class GLRenderer implements Renderer{
    @Override
    public void render() {
        glColor3f(0.0f, 0.5f, 0.5f);
        glBegin(GL_QUADS);
            glVertex2f(-0.5f, -0.5f);
            glVertex2f(0.5f, -0.5f);
            glVertex2f(0.5f, 0.5f);
            glVertex2f(-0.5f, 0.5f);
        glEnd();
    }

    @Override
    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
    }
}
