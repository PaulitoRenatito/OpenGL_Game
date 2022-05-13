import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

import java.awt.*;


import entities.*;
import types.*;
import managers.*;

public class Game {

    public void run() {

        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize");
            System.exit(1);
        }

        Screen screen = new Screen(1600, 900);

        glfwShowWindow(Screen.WINDOW);

        glfwMakeContextCurrent(Screen.WINDOW);

        GL.createCapabilities();

        Player player = new Player(5);

        World world = new World(player, screen);

        world.spawnEnemies();

        while (!glfwWindowShouldClose(Screen.WINDOW)) {

            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            screen.resizeScreen();

            world.limitBounds();

            player.getInputs();

            world.updateEnemies();

            world.updateProjectiles();
            world.checkCollision();

            glfwSwapBuffers(Screen.WINDOW);

        }
    }

    public static void main(String[] args) {
        new Game().run();
    }

}
