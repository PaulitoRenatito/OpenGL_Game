package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import gui.GameOver;
import gui.Menu;
import managers.*;
import org.lwjgl.opengl.GL;
import types.GameState;
import types.Timer;

public class Game {

    public static GameState GAME_STATE = GameState.MENU;

    public void run() {

        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize");
            System.exit(1);
        }

        GameManager gameManager = new GameManager();
        Menu menu = new Menu();
        GameOver gameOver = new GameOver();

        double frameCap = 1.0/60.0;

        double frame_time = 0;

        int fps = 0;

        double time = glfwGetTime();

        double unprocessed = 0;

        while (!GameManager.getWindow().shouldClose()) {

            boolean camRender = false;

            double time_2 = glfwGetTime();

            double passed = time_2 - time;

            unprocessed += passed;
            frame_time += passed;

            time = time_2;

            while (unprocessed >= frameCap) {

                unprocessed -= frameCap;

                camRender = true;

                gameManager.registerCallBacks();

                gameManager.getInputs();
                glfwPollEvents();

                if (frame_time >= 1.0) {
                    frame_time = 0;
                    fps = 0;
                }

            }

            if (camRender && !GameManager.isPaused()) {
                glClear(GL_COLOR_BUFFER_BIT);

                switch (GAME_STATE) {
                    case MENU -> menu.displayMenu();
                    case GAME -> gameManager.displayGame();
                    case GAME_OVER -> gameOver.displayGameOver();
                }

                GameManager.getWindow().swapBuffers();

                fps++;
            }

        }
    }

    public static void main(String[] args) {
        new Game().run();
    }

}
