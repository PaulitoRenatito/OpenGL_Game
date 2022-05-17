package managers;

import entities.Player;
import org.lwjgl.opengl.GL;
import types.Vector2;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameManager {
    private static boolean isPaused = false;

    private static Screen screen;

    private static Player player;

    private static LevelManager levelManager;

    public GameManager() {

        createScreen();

        player = new Player(5);
    }

    private void createScreen() {
        screen = new Screen(1600, 900);

        glfwShowWindow(Screen.WINDOW);

        glfwMakeContextCurrent(Screen.WINDOW);

        GL.createCapabilities();
    }

    public void startGame() {
        levelManager = new LevelManager();

        createWorld();
    }

    private void createWorld() {
        glViewport(0, 0, screen.getWidth(), screen.getHeight());
        glMatrixMode(GL_PROJECTION);
        glOrtho(-screen.getWidth(),
                screen.getWidth(),
                -screen.getHeight(), screen.getHeight(),
                -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public void updateGame() {

    }

    void writeOnScreen(Vector2 position, Color color, String text)
    {

    }

    public void getInputs() {
        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_P) == GL_TRUE) {
            isPaused = true;
        } else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_R) == GL_TRUE) {
            isPaused = false;
        }
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public static void setPaused(boolean isPaused) {
        GameManager.isPaused = isPaused;
    }

    public static Screen getScreen() {
        return screen;
    }

    public static void setScreen(Screen screen) {
        GameManager.screen = screen;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        GameManager.player = player;
    }

    public static LevelManager getLevelManager() {
        return levelManager;
    }

    public static void setLevelManager(LevelManager levelManager) {
        GameManager.levelManager = levelManager;
    }
}
