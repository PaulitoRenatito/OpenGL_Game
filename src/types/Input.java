package types;

import managers.Screen;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class Input {

    private static boolean pressing = false;

    public static boolean GetKey(KeyCode keyCode) {
        return glfwGetKey(Screen.WINDOW, keyCode.getValue()) == GL_TRUE;
    }

    public static boolean GetKeyDown(KeyCode keyCode) {

        if (!GetKey(keyCode)) {
            pressing = false;
            return false;
        }
        else {
            if (pressing) {
                return false;
            }
            else {
                pressing = true;
                return true;
            }
        }

    }

    public static boolean GetKeyUp(KeyCode keyCode) {

        if (GetKey(keyCode)) {
            pressing = true;
            return false;
        }
        else {
            if (pressing) {
                pressing = false;
                return true;
            }
            else {
                return false;
            }
        }

    }

    public static boolean GetMouseButton(KeyCode keyCode) {
        return glfwGetMouseButton(Screen.WINDOW, keyCode.getValue()) == GL_TRUE;
    }

}
