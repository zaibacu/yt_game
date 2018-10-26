package lt.griaustinis.ytgame.graphics;

import com.google.common.collect.Streams;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;

public class GLRenderer implements Renderer{
    @Override
    public void render(Drawable drawObj) {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glBindTexture(GL_TEXTURE_2D, drawObj.getTexture().getId());

        glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        glPushMatrix();
        glTranslatef(drawObj.getX(), drawObj.getY(), 1.0f);
        //glRotatef(45f, 0.0f, 0.0f, 1.0f);
        //glScalef(0.5f, 0.5f, 1.0f);
        // A, B. Zip(A, B) -> a1, b1 ; a2, b2; a3, b3; a4, b4.

        glScalef(drawObj.getTexture().getRatio(), 1.0f, 1.0f);

        glBegin(drawObj.getDrawType());
        Streams.zip(
                drawObj.getVertices().stream(),
                drawObj.getTexture().getTextureCoord().stream(),
                (v, t) -> new float[]{t.getU(), t.getV(), v.getX(), v.getY()}
        ).forEach(arr -> {
            glTexCoord2f(arr[0], arr[1]);
            glVertex2f(arr[2], arr[3]);
        });

        glEnd();
        glPopMatrix();

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

    @Override
    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
        glClearColor(0.0f, 1.0f, 1.0f, 1.0f);
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
