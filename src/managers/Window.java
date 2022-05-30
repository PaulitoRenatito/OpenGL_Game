package managers;

import entities.Entity;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import types.Vector2;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private int width;
    private int height;

    private float aspect;

    public static long WINDOW;

    private boolean isFullScreen = false;

    public Window() {
        setSize(1600, 900);
    }

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
        aspect = (float)width / (float)height;
    }

    public void createWindow(String title) {

        if (isFullScreen) {
            WINDOW = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), 0);
        }
        else {

            WINDOW = glfwCreateWindow(width, height, title, 0, 0);
        }

        if (WINDOW == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        if (!isFullScreen) {
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(WINDOW,
                    (vidMode.width() - width)/2,
                    (vidMode.height() - height)/2);

            glfwShowWindow(WINDOW);
        }

        glfwMakeContextCurrent(WINDOW);
    }

    public void setCallBacks() {
        glfwSetWindowSizeCallback(WINDOW, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                reshape(width, height);
            }
        });
    }

    private void reshape(int width, int height) {
        final float ar_origin = (float) this.width / (float) this.height;
        final float ar_new = (float) width / (float) height;

        float scale_w = (float) width / (float) this.width;
        float scale_h = (float) height / (float) this.height;

        if (ar_new > ar_origin) {
            scale_w = scale_h;
        }
        else {
            scale_h = scale_w;
        }

        glViewport(0, 0, (int) (this.width * scale_w), (int) (this.height * scale_h));
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-this.width, this.width,
                -this.height, this.height,
                -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(WINDOW);
    }

    public void swapBuffers() {
        glfwSwapBuffers(WINDOW);
    }

    public boolean isOutOfScreen(Entity entity) {

        int boundY = height;
        Vector2 entityPosition = entity.getTransform().getPosition();
        float entitySize = entity.getTransform().getSize();

        if (entityPosition.getY() -  entitySize > boundY ||
                entityPosition.getY() + entitySize < -boundY) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean isBelowTheScreen(Entity entity) {

        int boundY = height;
        Vector2 entityPosition = entity.getTransform().getPosition();
        float entitySize = entity.getTransform().getSize();

        if (entityPosition.getY() + entitySize < -boundY) {
            return true;
        }
        else {
            return false;
        }

    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }
}
