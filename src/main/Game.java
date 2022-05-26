package main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import managers.*;
import org.lwjgl.opengl.GL;
import types.Timer;

public class Game {

    public void run() {

        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize");
            System.exit(1);
        }

        GameManager gameManager = new GameManager();

        gameManager.startGame();

        double frameCap = 1.0/60.0;

        double frame_time = 0;

        int fps = 0;

        double time = Timer.getTime();

        double unprocessed = 0;

        while (!GameManager.getWindow().shouldClose()) {

            boolean camRender = false;

            double time_2 = Timer.getTime();

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
                    // System.out.println("FPS: " + fps);
                    fps = 0;
                }

            }

            if (camRender && !GameManager.isPaused()) {
                glClear(GL_COLOR_BUFFER_BIT);

                // GameManager.getPlayer().getInputs();

                GameManager.getLevelManager().updateLevel();

                GameManager.getWindow().swapBuffers();

                fps++;
            }

        }
    }

    public static void main(String[] args) {
        new Game().run();
    }

}
