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

        Enemy enemy1 = new Enemy(new Transform(60f, new Vector2(800f, 800f), new Color(1, 0, 0, 1)), 1);
        Enemy enemy2 = new Enemy(new Transform(60f, new Vector2(100f, 900f), new Color(1, 0, 0, 1)), 1);
        Enemy enemy3 = new Enemy(new Transform(60f, new Vector2(-800f, 750f), new Color(1, 0, 0, 1)), 1);
        Enemy enemy4 = new Enemy(new Transform(60f, new Vector2(-200f, 8200f), new Color(1, 0, 0, 1)), 1);
        Enemy enemy5 = new Enemy(new Transform(60f, new Vector2(1200f, 900f), new Color(1, 0, 0, 1)), 1);

        World world = new World(player, screen);

        Time time = new Time();

        while (!glfwWindowShouldClose(Screen.WINDOW)) {

            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            screen.resizeScreen();

            world.limitBounds();

            player.getInputs();

            enemy1.updateMovement();
            enemy2.updateMovement();
            enemy3.updateMovement();
            enemy4.updateMovement();
            enemy5.updateMovement();

            world.updateProjectiles();

            glfwSwapBuffers(Screen.WINDOW);
        }
    }

    public static void main(String[] args) {
        new Game().run();
    }

}
