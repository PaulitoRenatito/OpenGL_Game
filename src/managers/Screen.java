package managers;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;

public class Screen {

    private int width = 1600;
    private int height = 900;

    private float aspect;

    public static long WINDOW;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        aspect = (float)width / (float)height;
        WINDOW = glfwCreateWindow(width, height, "Window", 0, 0);
    }

    public void resizeScreen() {

        GLFWWindowSizeCallback windowSizeCallback;

        glfwSetWindowSizeCallback(WINDOW, windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height){
                setWidth(width);
                setHeight(height);
                setAspect((float)width / (float)height);
            }
        });

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getAspect() {
        return aspect;
    }

    public void setAspect(float aspect) {
        this.aspect = aspect;
    }
}
