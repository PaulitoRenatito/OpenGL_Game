import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import managers.*;

public class Game {

    public void run() {

        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize");
            System.exit(1);
        }

        GameManager gameManager = new GameManager();

        gameManager.startGame();

        while (!glfwWindowShouldClose(Screen.WINDOW)) {

            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            GameManager.getPlayer().getInputs();

            GameManager.getLevelManager().updateLevel();

            glfwSwapBuffers(Screen.WINDOW);

        }
    }

    public static void main(String[] args) {
        new Game().run();
    }

}
