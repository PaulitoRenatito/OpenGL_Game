package managers;

import types.Vector2;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameManager {

    private static GameManager instance = null;
    public static boolean isPaused = false;

    private GameManager() {

    }

    public static GameManager getInstance() {

        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    public void startGame() {

    }

    public void updateGame() {

    }

    void writeOnScreen(Vector2 position, Color color, String text)
    {
        setD
    }

    public void getInputs() {
        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_P) == GL_TRUE) {
            isPaused = true;
        } else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_R) == GL_TRUE) {
            isPaused = false;
        }
    }

}
