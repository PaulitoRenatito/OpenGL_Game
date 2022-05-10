import static org.lwjgl.opengl.GL11.*;

public class Movement {

    private float size = 0.1f;

    private float positionX;
    private float positionY;

    private float startPositionX = 0;
    private float startPositionY = 0;

    public Movement() {
        glBegin(GL_QUADS);
            glColor4f(1, 0, 0, 0);
            glVertex2f(-size + startPositionX, size + startPositionY);

            glColor4f(0, 1, 0, 0);
            glVertex2f(size + startPositionX, size + startPositionY);

            glColor4f(0, 0, 1, 0);
            glVertex2f(size + startPositionX, -size + startPositionY);

            glColor4f(1, 1, 1, 0);
            glVertex2f(-size + startPositionX, -size + startPositionY);
        glEnd();
    }

    public Movement(float size, float startPositionX, float startPositionY) {
        this.size = size;
        this.startPositionX = startPositionX;
        this.startPositionY = startPositionY;
        glBegin(GL_QUADS);
            glColor4f(1, 0, 0, 0);
            glVertex2f(-size + startPositionX, size + startPositionY);

            glColor4f(0, 1, 0, 0);
            glVertex2f(size + startPositionX, size + startPositionY);

            glColor4f(0, 0, 1, 0);
            glVertex2f(size + startPositionX, -size + startPositionY);

            glColor4f(1, 1, 1, 0);
            glVertex2f(-size + startPositionX, -size + startPositionY);
        glEnd();
    }

    public void updateMovement(Vector2 vector2) {

        glBegin(GL_QUADS);
            glColor4f(1, 0, 0, 0);
            glVertex2f(-size + vector2.getX(), size + vector2.getY());

            glColor4f(0, 1, 0, 0);
            glVertex2f(size + vector2.getX(), size + vector2.getY());

            glColor4f(0, 0, 1, 0);
            glVertex2f(size + vector2.getX(), -size + vector2.getY());

            glColor4f(1, 1, 1, 0);
            glVertex2f(-size + vector2.getX(), -size + vector2.getY());
        glEnd();

    }

    public void MoveUp(Vector2 vector2) {

        vector2.incrementY(0.05f);

        glBegin(GL_QUADS);
        glColor4f(1, 0, 0, 0);
        glVertex2f(-size + startPositionX, size + vector2.getY() + startPositionY);

        glColor4f(0, 1, 0, 0);
        glVertex2f(size + startPositionX, size + vector2.getY() + startPositionY);

        glColor4f(0, 0, 1, 0);
        glVertex2f(size + startPositionX, -size + vector2.getY() + startPositionY);

        glColor4f(1, 1, 1, 0);
        glVertex2f(-size + startPositionX, -size + vector2.getY() + startPositionY);
        glEnd();

    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getStartPositionX() {
        return startPositionX;
    }

    public void setStartPositionX(float startPositionX) {
        this.startPositionX = startPositionX;
    }

    public float getStartPositionY() {
        return startPositionY;
    }

    public void setStartPositionY(float startPositionY) {
        this.startPositionY = startPositionY;
    }
}
