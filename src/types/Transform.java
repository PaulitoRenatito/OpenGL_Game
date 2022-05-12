package types;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Transform {

    private float size;

    private Vector2 position;

    private Color color;

    public Transform() {

        size = 60f;
        position = new Vector2();
        color = new Color(1, 1, 1, 1);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size, size);


            glVertex2f(size, size);


            glVertex2f(size, -size);


            glVertex2f(-size, -size);

        glEnd();

    }

    public Transform(float size, Vector2 position, Color color) {

        this.size = size;
        this.position = position;
        this.color = color;

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), -size + position.getY());


            glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

    }

    public void updateMovement(Vector2 movement) {

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + movement.getX(), size + movement.getY());

            glVertex2f(size + movement.getX(), size + movement.getY());

            glVertex2f(size + movement.getX(), -size + movement.getY());

            glVertex2f(-size + movement.getX(), -size + movement.getY());

        glEnd();

    }

    public void MoveUp(Vector2 movement, float speed) {

        movement.incrementY(5f * speed);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + movement.getY());


            glVertex2f(size + position.getX(), size + movement.getY());


            glVertex2f(size + position.getX(), -size + movement.getY());


            glVertex2f(-size + position.getX(), -size + movement.getY());

        glEnd();

    }

    public void MoveDown(Vector2 movement, float speed) {

        movement.incrementY(-5f * speed);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + position.getY() + movement.getY());


            glVertex2f(size + position.getX(), size + position.getY() + movement.getY());


            glVertex2f(size + position.getX(), -size + position.getY() + movement.getY());


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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
