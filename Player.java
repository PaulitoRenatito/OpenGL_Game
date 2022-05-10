import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player {

    private float speed = 0.02f;

    private Transform transform = new Transform();

    private ArrayList<Projectile> projectiles = new ArrayList<>();

    private float cooldown = 2f;
    private float time = 0f;

    public Player() {

    }

    public void getInputs() {

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_FALSE) {
            transform.getPosition().incrementX(-speed);
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_FALSE) {
            transform.getPosition().incrementX(speed);
            transform.updateMovement(transform.getPosition());
        }

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_FALSE) {
            transform.getPosition().incrementY(-speed);
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_FALSE) {
            transform.getPosition().incrementY(speed);
            transform.updateMovement(transform.getPosition());
        }

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(-speed/1.25f, -speed/1.25f));
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(-speed/1.25f, speed/1.25f));
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(speed/1.25f, -speed/1.25f));
            transform.updateMovement(transform.getPosition());
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE) {
            transform.getPosition().increment(new Vector2(speed/1.25f, speed/1.25f));
            transform.updateMovement(transform.getPosition());
        }

        if (glfwGetMouseButton(Screen.WINDOW, GLFW_MOUSE_BUTTON_1) == GL_TRUE && time > cooldown) {
            time = 0;
            Projectile projectile = new Projectile(0.05f, new Vector2(transform.getPosition().getX(),
                    transform.getPosition().getY() + transform.getSize()));
            projectiles.add(projectile);
        }

        if (projectiles.size() > 0) {
            for (Projectile projectile: projectiles) {
                projectile.getTransform().MoveUp(
                        projectile.getTransform().getPosition(),
                        projectile.getSpeed());
            }
        }

        time += 0.1;

        transform.updateMovement(transform.getPosition());
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
