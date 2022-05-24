package types;
import managers.Window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class Input {

    private static boolean pressing = false;

    private static boolean keys[] = new boolean[KeyCode.values().length];

    public static boolean GetKey(KeyCode keyCode) {
        return glfwGetKey(Window.WINDOW, keyCode.getValue()) == GL_TRUE;
    }

    public static boolean GetKeyDown(KeyCode keyCode) {

        return (GetKey(keyCode) && !keys[keyCode.ordinal()]);

    }

    public static boolean GetKeyUp(KeyCode keyCode) {

        return (!GetKey(keyCode) && keys[keyCode.ordinal()]);

    }

    public static boolean GetMouseButton(KeyCode keyCode) {
        return glfwGetMouseButton(Window.WINDOW, keyCode.getValue()) == GL_TRUE;
    }

    public static boolean GetMouseButtonDown(KeyCode keyCode) {
        return (GetMouseButton(keyCode) && !keys[keyCode.ordinal()]);
    }

    public static boolean GetMouseButtonUp(KeyCode keyCode) {
        return (!GetMouseButton(keyCode) && keys[keyCode.ordinal()]);
    }

    public static void update() {
        int i = 0;
        for (KeyCode key: KeyCode.values()) {
            keys[i] = GetKey(key);
            i++;
        }
    }
}
