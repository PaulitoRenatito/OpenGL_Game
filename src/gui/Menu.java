package gui;

import main.Game;
import managers.GameManager;
import managers.Window;
import types.GameState;
import types.Input;
import types.KeyCode;
import types.Sprite;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class Menu {

    Sprite background = new Sprite("res/backgroundMenu.png");

    public void displayMenu() {

        displayBackgroundImage();

        getInputs();

    }

    private void getInputs() {
        if (Input.GetKeyDown(KeyCode.J)) {
            Game.GAME_STATE = GameState.GAME;
        }
        else if (Input.GetKeyDown(KeyCode.ESC)) {
            glfwSetWindowShouldClose(Window.WINDOW, true);
        }
    }

    private void displayBackgroundImage() {
        glClear(GL_COLOR_BUFFER_BIT);

        glColor3f(1f, 1f,1f);

        glEnable(GL_TEXTURE_2D);

        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glBindTexture(GL_TEXTURE_2D, background.getId());

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(-GameManager.getWindow().getWidth(), GameManager.getWindow().getHeight());
        glTexCoord2f(1, 0);
        glVertex2f(GameManager.getWindow().getWidth(), GameManager.getWindow().getHeight());
        glTexCoord2f(1, 1);
        glVertex2f(GameManager.getWindow().getWidth(), -GameManager.getWindow().getHeight());
        glTexCoord2f(0, 1);
        glVertex2f(-GameManager.getWindow().getWidth(), -GameManager.getWindow().getHeight());
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
