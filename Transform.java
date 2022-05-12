import static org.lwjgl.opengl.GL11.*;

public class Transform {

    private float size;

    private Vector2 position;

    private float length;

    public Transform() {

        size = 0.1f;
        position = new Vector2();
        // length = size * 2;

        glBegin(GL_QUADS);
            glColor4f(1, 1, 1, 0);
            glVertex2f(-size, size);

            glColor4f(1, 1, 1, 0);
            glVertex2f(size, size);

            glColor4f(1, 1, 1, 0);
            glVertex2f(size, -size);

            glColor4f(1, 1, 1, 0);
            glVertex2f(-size, -size);
        glEnd();

    }

    public Transform(float size, Vector2 position) {

        this.size = size;
        this.position = position;
        // length = new Vector2(position.getX() + size, position.getY() + size);

        glBegin(GL_QUADS);
        glColor4f(1, 1, 1, 0);
        glVertex2f(-size + position.getX(), size + position.getY());

        glColor4f(1, 1, 1, 0);
        glVertex2f(size + position.getX(), size + position.getY());

        glColor4f(1, 1, 1, 0);
        glVertex2f(size + position.getX(), -size + position.getY());

        glColor4f(1, 1, 1, 0);
        glVertex2f(-size + position.getX(), -size + position.getY());
        glEnd();

    }

    public void updateMovement(Vector2 movement) {

        glBegin(GL_QUADS);
        glColor4f(1, 0, 0, 0);
        glVertex2f(-size + movement.getX(), size + movement.getY());

        glColor4f(0, 1, 0, 0);
        glVertex2f(size + movement.getX(), size + movement.getY());

        glColor4f(0, 0, 1, 0);
        glVertex2f(size + movement.getX(), -size + movement.getY());

        glColor4f(1, 1, 1, 0);
        glVertex2f(-size + movement.getX(), -size + movement.getY());
        glEnd();

    }

    public void MoveUp(Vector2 movement, float speed) {

        movement.incrementY(0.05f * speed);

        glBegin(GL_QUADS);
            glColor4f(1, 0, 0, 0);
            glVertex2f(-size + position.getX(), size + movement.getY());

            glColor4f(0, 1, 0, 0);
            glVertex2f(size + position.getX(), size + movement.getY());

            glColor4f(0, 0, 1, 0);
            glVertex2f(size + position.getX(), -size + movement.getY());

            glColor4f(1, 1, 1, 0);
            glVertex2f(-size + position.getX(), -size + movement.getY());
        glEnd();

    }

    public void MoveDown(Vector2 movement, float speed) {

        movement.incrementY(-0.05f * speed);

        glBegin(GL_QUADS);
        glColor4f(1, 0, 0, 0);
        glVertex2f(-size + position.getX(), size + position.getY() + movement.getY());

        glColor4f(0, 1, 0, 0);
        glVertex2f(size + position.getX(), size + position.getY() + movement.getY());

        glColor4f(0, 0, 1, 0);
        glVertex2f(size + position.getX(), -size + position.getY() + movement.getY());

        glColor4f(1, 1, 1, 0);
        glVertex2f(-size + position.getX(), -size + position.getY() + movement.getY());
        glEnd();

    }

    public void moveTowards(Vector2 target) {
        position.increment(target.subtract(position).divided(50f)); // myPosition += (target - myPosition) / 50f
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
