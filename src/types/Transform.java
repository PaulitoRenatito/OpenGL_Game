package types;
import managers.GameManager;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Transform {

    private float size;

    private boolean startMovingRight = true;

    private boolean blink = false;

    private float incrementX = 2.5f;

    private Vector2 position;

    private Color color;

    private Sprite sprite;

    private Collider collider;

    private float timer = 0f;
    private float timerBlink = 0f;

    public Transform() {

        size = (GameManager.getWindow().getWidth()*60)/1600f;
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

        this.size = (GameManager.getWindow().getWidth()*size)/1600f;
        this.position = position;
        this.sprite = sprite;
        color = new Color(1, 1, 1, 1);
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

        updateColor();

        collider.updateCollider(position, size);

        glColor3f(color.getRed(), color.getGreen(), color.getBlue());

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

    public void Blink(Color color) {
        blink = true;
    }

    public void updateColor() {

        if (blink) {

            if (timerBlink < 0.25f) {
                this.color = Color.RED;
            }
            else if(timerBlink < 0.5f) {
                this.color = Color.WHITE;
            }
            else if(timerBlink < 0.75f) {
                this.color = Color.RED;
            }
            else if (timerBlink < 1f) {
                this.color = Color.WHITE;
            }
            else if(timerBlink < 1.25f) {
                this.color = Color.RED;
            }
            else if(timerBlink < 1.5f) {
                this.color = Color.WHITE;
            }
            else if(timerBlink < 1.75f) {
                this.color = Color.RED;
            }
            else if(timerBlink < 2f) {
                this.color = Color.WHITE;
                timerBlink = 0;
                blink = false;
            }

            timerBlink += 0.02f;
        }
    }

    public void MoveUp(float speed) {

        speed = (GameManager.getWindow().getWidth() * speed)/1600f;

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

        speed = (GameManager.getWindow().getWidth() * speed) / 1600f;

        position.incrementY(-5f * speed);

        collider.updateCollider(position, size);

        glColor3f(1, .9f, 0);

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

    public void MoveDownZigZagging(float speed, float switchSideTime) {

        speed = ((GameManager.getWindow().getWidth() * speed)/1600f);

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

    public void MoveDownZigZagging(float speed, float zigzagSpeed, float switchSideTime) {

        updateColor();

        speed = (GameManager.getWindow().getWidth() * speed)/1600f;

        if (timer > switchSideTime) {
            timer = 0;
            incrementX *= -1;
        }

        position.incrementY(-5f * speed);
        position.incrementX(incrementX * zigzagSpeed);

        collider.updateCollider(position, size);

        glColor3f(color.getRed(), color.getGreen(), color.getBlue());

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

        Vector2 dir = new Vector2();
        dir.increment(target.subtract(position));

        float hyp = (float) Math.sqrt(Math.pow(dir.getX(), 2) + Math.pow(dir.getY(), 2));
        dir.setX(dir.getX()/hyp);
        dir.setY(dir.getY()/hyp);

        position.increment(dir.multiply(4f));

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

    public boolean isBlink() {
        return blink;
    }

    public void setBlink(boolean blink) {
        this.blink = blink;
    }
}
