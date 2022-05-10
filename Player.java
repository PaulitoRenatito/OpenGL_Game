import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Player {

    private float speed = 0.02f;

    private Movement movement = new Movement();
    private Vector2 vector2 = new Vector2();

    private Projectile projectile = null;

    public Player(long window) {
        Screen.WINDOW = window;
    }

    public void getInputs() {

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_FALSE) {
            vector2.incrementX(-speed);
            movement.updateMovement(vector2);
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_FALSE) {
            vector2.incrementX(speed);
            movement.updateMovement(vector2);
        }

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_FALSE) {
            vector2.incrementY(-speed);
            movement.updateMovement(vector2);
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_FALSE &&
                glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_FALSE) {
            vector2.incrementY(speed);
            movement.updateMovement(vector2);
        }

        if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE) {
            vector2.incrementX(-speed/1.25f);
            vector2.incrementY(-speed/1.25f);
            movement.updateMovement(vector2);
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_A) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE) {
            vector2.incrementX(-speed/1.25f);
            vector2.incrementY(speed/1.25f);
            movement.updateMovement(vector2);
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_S) == GL_TRUE) {
            vector2.incrementX(speed/1.25f);
            vector2.incrementY(-speed/1.25f);
            movement.updateMovement(vector2);
        }
        else if (glfwGetKey(Screen.WINDOW, GLFW_KEY_D) == GL_TRUE && glfwGetKey(Screen.WINDOW, GLFW_KEY_W) == GL_TRUE) {
            vector2.incrementX(speed/1.25f);
            vector2.incrementY(speed/1.25f);
            movement.updateMovement(vector2);
        }

        if (glfwGetMouseButton(Screen.WINDOW, GLFW_MOUSE_BUTTON_1) == GL_TRUE) {
            projectile = new Projectile(vector2.getX(), vector2.getY() + movement.getSize());
        }

        if (projectile != null) {
            projectile.updateProjectile();
        }


        

        movement.updateMovement(vector2);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public Vector2 getVector2() {
        return vector2;
    }

    public void setVector2(Vector2 vector2) {
        this.vector2 = vector2;
    }
}
