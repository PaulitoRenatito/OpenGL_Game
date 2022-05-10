import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import java.nio.IntBuffer;

public class HelloWorld {

    public void run() {

        if (!glfwInit()) {
            System.err.println("GLFW Failed to initialize");
            System.exit(1);
        }

        // The window handle
        // long window = glfwCreateWindow(1600, 900, "Window", 0, 0);

        // System.out.println(Screen.WINDOW);

        Screen screen = new Screen(1600, 900);

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(Screen.WINDOW, w, h);
        int width = w.get(0);
        int height = h.get(0);

        System.out.println("width: " + width);
        System.out.println("height: " + height);

        glfwShowWindow(Screen.WINDOW);

        glfwMakeContextCurrent(Screen.WINDOW);

        GL.createCapabilities();

        Player player = new Player(Screen.WINDOW);

        World world = new World(player, screen);

        while (!glfwWindowShouldClose(Screen.WINDOW)) {

            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT);

            screen.resizeScreen();

            world.limitBounds();

            System.out.println("Player X: " + player.getVector2().getX());
            System.out.println("Player Y: " + player.getVector2().getY());

            player.getInputs();

            glfwSwapBuffers(Screen.WINDOW);
        }
    }

    public static void main(String[] args) {
        new HelloWorld().run();
    }

}
