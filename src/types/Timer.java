package types;

import static org.lwjgl.glfw.GLFW.*;

public class Timer {

    public static double getTime() {
        return glfwGetTime();
    }

}
