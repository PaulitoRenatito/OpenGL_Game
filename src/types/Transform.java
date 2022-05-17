package types;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class Transform {

    private float size;

    private boolean startMovingRight = true;

    private float incrementX = 2.5f;

    private Vector2 position;

    private Color color;

    private Collider collider;

    public Transform() {

        size = 60f;
        position = new Vector2();
        color = new Color(1, 1, 1, 1);
        collider = new Collider(position, size);

//        BufferedImage image = Texture.loadImage("/res/ship1.png");
//        int textureID = Texture.loadTexture(image);
//
//        glEnable(GL_TEXTURE_2D);
//
//        glBindTexture(GL_TEXTURE_2D, textureID);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size, size);


            glVertex2f(size, size);


            glVertex2f(size, -size);


            glVertex2f(-size, -size);

        glEnd();

//        glDisable(GL_TEXTURE_2D);

    }

    public Transform(float size, Vector2 position, Color color) {

        this.size = size;
        this.position = position;
        this.color = color;
        collider = new Collider(position, size);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), -size + position.getY());


            glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

    }

    public void updateMovement(Vector2 movement) {

        collider.updateCollider(position, size);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + movement.getX(), size + movement.getY());

            glVertex2f(size + movement.getX(), size + movement.getY());

            glVertex2f(size + movement.getX(), -size + movement.getY());

            glVertex2f(-size + movement.getX(), -size + movement.getY());

        glEnd();

    }

    public void MoveUp(float speed) {

        position.incrementY(5f * speed);

        collider.updateCollider(position, size);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), -size + position.getY());


            glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

    }

    public void MoveDown(float speed) {

        position.incrementY(-5f * speed);

        collider.updateCollider(position, size);

        glBegin(GL_QUADS);

            glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

            glVertex2f(-size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), size + position.getY());


            glVertex2f(size + position.getX(), -size + position.getY());


            glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

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

        glBegin(GL_QUADS);

        glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());

        glVertex2f(-size + position.getX(), size + position.getY());


        glVertex2f(size + position.getX(), size + position.getY());


        glVertex2f(size + position.getX(), -size + position.getY());


        glVertex2f(-size + position.getX(), -size + position.getY());

        glEnd();

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
