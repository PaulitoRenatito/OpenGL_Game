package types;
import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Transform {

    private float size;

    private boolean startMovingRight = true;

    private float incrementX = 2.5f;

    private Vector2 position;

    private Color color;

    private Sprite sprite;

    private Collider collider;

    public Transform() {

        size = 60f;
        position = new Vector2();
        color = new Color(1, 1, 1, 1);
        collider = new Collider(position, size);
        sprite = new Sprite("res/ship1.png");

        // glClear(GL_COLOR_BUFFER_BIT);

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glColor3f(1, 1, 1);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex3f(-size, size, 0);

        glTexCoord2f(1, 0);
        glVertex3f(size, size, 0);

        glTexCoord2f(1, 1);
        glVertex3f(size, -size, 0);

        glTexCoord2f(0, 1);
        glVertex3f(-size, -size, 0);

        glEnd();

        sprite.disableImage();

    }

    public Transform(float size, Vector2 position, Sprite sprite) {

        this.size = size;
        this.position = position;
        this.sprite = sprite;
        collider = new Collider(position, size);

        this.sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex3f(-size + position.getX(), size + position.getY(), 0);

        glTexCoord2f(1, 0);
        glVertex3f(size + position.getX(), size + position.getY(), 0);

        glTexCoord2f(1, 1);
        glVertex3f(size + position.getX(), -size + position.getY(), 0);

        glTexCoord2f(0, 1);
        glVertex3f(-size + position.getX(), -size + position.getY(), 0);

        glEnd();

        this.sprite.disableImage();

    }

    public void updateMovement(Vector2 movement) {

        collider.updateCollider(position, size);

        // glClear(GL_COLOR_BUFFER_BIT);

        glColor3f(1, 1, 1);

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex3f(-size + movement.getX(), size + movement.getY(), 0);

        glTexCoord2f(1, 0);
        glVertex3f(size + movement.getX(), size + movement.getY(), 0);

        glTexCoord2f(1, 1);
        glVertex3f(size + movement.getX(), -size + movement.getY(), 0);

        glTexCoord2f(0, 1);
        glVertex3f(-size + movement.getX(), -size + movement.getY(), 0);

        glEnd();

        sprite.disableImage();
    }

    public void MoveUp(float speed) {

        position.incrementY(5f * speed);

        collider.updateCollider(position, size);

        glColor3f(1, 1, 1);

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(-size + position.getX(), size + position.getY());

        glTexCoord2f(1, 0);
        glVertex2f(size + position.getX(), size + position.getY());

        glTexCoord2f(1, 1);
        glVertex2f(size + position.getX(), -size + position.getY());

        glTexCoord2f(0, 1);
        glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

        sprite.disableImage();

    }

    public void MoveDown(float speed) {

        position.incrementY(-5f * speed);

        collider.updateCollider(position, size);

        glColor3f(1, 1, 1);

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(-size + position.getX(), size + position.getY());

        glTexCoord2f(1, 0);
        glVertex2f(size + position.getX(), size + position.getY());

        glTexCoord2f(1, 1);
        glVertex2f(size + position.getX(), -size + position.getY());

        glTexCoord2f(0, 1);
        glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

        sprite.disableImage();

    }

    private float timer = 0f;

    public void MoveDownZigZagging(float speed, int switchSideTime) {

        if (timer > switchSideTime) {
            timer = 0;
            incrementX *= -1;
        }

        position.incrementY(-5f * speed);
        position.incrementX(incrementX * speed);

        collider.updateCollider(position, size);

        glColor3f(1, 1, 1);

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(-size + position.getX(), size + position.getY());

        glTexCoord2f(1, 0);
        glVertex2f(size + position.getX(), size + position.getY());

        glTexCoord2f(1, 1);
        glVertex2f(size + position.getX(), -size + position.getY());

        glTexCoord2f(0, 1);
        glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

        sprite.disableImage();

        timer += 0.02f;
    }

    public void MoveDownZigZagging(float speed, float zigzagSpeed, int switchSideTime) {

        if (timer > switchSideTime) {
            timer = 0;
            incrementX *= -1;
        }

        position.incrementY(-5f * speed);
        position.incrementX(incrementX * zigzagSpeed);

        collider.updateCollider(position, size);

        glColor3f(1, 1, 1);

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(-size + position.getX(), size + position.getY());

        glTexCoord2f(1, 0);
        glVertex2f(size + position.getX(), size + position.getY());

        glTexCoord2f(1, 1);
        glVertex2f(size + position.getX(), -size + position.getY());

        glTexCoord2f(0, 1);
        glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

        sprite.disableImage();

        timer += 0.02f;
    }

    public void moveTowards(Vector2 target) {

        position.increment(target.subtract(position).divided(80f)); // myPosition += (target - myPosition) / 50f

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), -size + position.getY());


            glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

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

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public boolean isStartMovingRight() {
        return startMovingRight;
    }

    public void setStartMovingRight(boolean startMovingRight) {
        this.startMovingRight = startMovingRight;
        if (startMovingRight) {
            setIncrementX(2.5f);
        }
        else {
            setIncrementX(-2.5f);
        }
    }

    public float getIncrementX() {
        return incrementX;
    }

    public void setIncrementX(float incrementX) {
        this.incrementX = incrementX;
    }
}
