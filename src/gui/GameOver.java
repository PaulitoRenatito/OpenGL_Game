package gui;

import main.Game;
import managers.GameManager;
import managers.LevelManager;
import managers.Window;
import org.lwjgl.BufferUtils;
import types.GameState;
import types.Input;
import types.KeyCode;
import types.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class GameOver {

    Sprite background = new Sprite("res/background.png");

    Sprite gameOver = new Sprite("res/backgroundGameOver.png");

    Sound sound = new Sound("res/gameOver.wav");

    boolean aux = true;

    public void displayGameOver() {

        displayBackgroundImage();

        displayGameOverImage();

        getInputs();

        if (aux) {
            sound.play();
            aux = false;
        }

    }

    private void getInputs() {
        if (Input.GetKeyDown(KeyCode.J)) {
            GameManager.setLevelManager(null);
            GameManager.setPlayer(null);
            LevelManager.enemies.clear();
            LevelManager.projectiles.clear();
            Game.GAME_STATE = GameState.MENU;
        }
        else if (Input.GetKeyDown(KeyCode.ESC)) {
            glfwSetWindowShouldClose(Window.WINDOW, true);
        }
    }

    private void displayBackgroundImage() {
        glClear(GL_COLOR_BUFFER_BIT);

        glColor3f(.3f, .3f,.3f);

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

    private void displayGameOverImage() {

        glColor3f(1f, 1f,1f);

        glEnable(GL_TEXTURE_2D);

        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glBindTexture(GL_TEXTURE_2D, gameOver.getId());

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(-GameManager.getWindow().getWidth()/2f, GameManager.getWindow().getHeight()/2f);
        glTexCoord2f(1, 0);
        glVertex2f(GameManager.getWindow().getWidth()/2f, GameManager.getWindow().getHeight()/2f);
        glTexCoord2f(1, 1);
        glVertex2f(GameManager.getWindow().getWidth()/2f, -GameManager.getWindow().getHeight()/2f);
        glTexCoord2f(0, 1);
        glVertex2f(-GameManager.getWindow().getWidth()/2f, -GameManager.getWindow().getHeight()/2f);
        glEnd();

        glDisable(GL_TEXTURE_2D);

    }
}
