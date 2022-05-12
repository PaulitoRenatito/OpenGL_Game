package entities;

import managers.*;
import types.*;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Creature {

    private float cooldown = 2f;
    private float time = 0f;

    public Player(int health) {
        super(health);
    }

    public void getInputs() {

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_FALSE) {
            transform.getPosition().incrementX(-getSpeed());
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_FALSE) {
            transform.getPosition().incrementX(getSpeed());
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_FALSE) {
            transform.getPosition().incrementY(-getSpeed());
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_FALSE) {
            transform.getPosition().incrementY(getSpeed());
            transform.updateMovement(transform.getPosition());
        }

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(-getSpeed()/1.25f, -getSpeed()/1.25f));
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(-getSpeed()/1.25f, getSpeed()/1.25f));
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(getSpeed()/1.25f, -getSpeed()/1.25f));
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(getSpeed()/1.25f, getSpeed()/1.25f));
            transform.updateMovement(transform.getPosition());
        }

        if (glfwGetMouseButton(Screen.WINDOW, GLFW_MOUSE_BUTTON_1) == GL_TRUE && time > cooldown) {
            time = 0;
            Projectile projectile = new Projectile(new Transform(20f, new Vector2(transform.getPosition().getX(),
                    transform.getPosition().getY() + transform.getSize()), new Color(0.6f, 0.6f, 0, 1)));
            World.projectiles.add(projectile);
        }

        time += 0.1;

        transform.updateMovement(transform.getPosition());
    }

}
