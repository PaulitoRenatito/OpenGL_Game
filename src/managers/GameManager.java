package managers;

import entities.Player;
import org.lwjgl.opengl.GL;
import types.Input;
import types.KeyCode;
import types.Sprite;
import types.Vector2;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameManager {
    private static boolean isPaused = false;

    public static boolean gameOver = false;

    private static Window window;

    private static Player player;

    private static LevelManager levelManager;

    public GameManager() {

        createWindow();

    }

    private void createWindow() {
        window = new Window();
        window.createWindow("Space Macetation");
        GL.createCapabilities();
    }

    public void startGame() {
        levelManager = new LevelManager();

        createWorld();

    }

    private void createWorld() {
        glViewport(0, 0, window.getWidth(), window.getHeight());
        glMatrixMode(GL_PROJECTION);
        glOrtho(-window.getWidth(), window.getWidth(),
                -window.getHeight(), window.getHeight(),
                -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    public void getInputs() {

        if (Input.GetKeyDown(KeyCode.P)) {
            isPaused = !isPaused;
        }
        else if (Input.GetKeyDown(KeyCode.R) && isPaused) {
            isPaused = false;
        }

        if(Input.GetKeyDown(KeyCode.ESC)) {
            glfwSetWindowShouldClose(Window.WINDOW, true);
        }

        Input.update();

    }

    public void registerCallBacks() {
        window.setCallBacks();
    }

    public static Window getWindow() {
        return window;
    }

    public static void setWindow(Window window) {
        GameManager.window = window;
    }

    public static boolean isPaused() {
        return isPaused;
    }

    public static void setPaused(boolean isPaused) {
        GameManager.isPaused = isPaused;
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
