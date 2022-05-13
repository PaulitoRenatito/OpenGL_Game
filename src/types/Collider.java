package types;

import entities.Entity;

public class Collider {

    Vector2 position;
    float size;

    public Collider(Vector2 position, float size) {
        this.position = position;
        this.size = size;
    }

    public void updateCollider(Vector2 position, float size) {
        this.position = position;
        this.size = size;
    }

    public boolean collidingWith(Entity entity) {

        if (position.getY() + size < entity.getTransform().getPosition().getY() - entity.getTransform().getSize())
            return false;
        else if (position.getY() - size > entity.getTransform().getPosition().getY() + entity.getTransform().getSize())
            return false;
        else if (position.getX() + size < entity.getTransform().getPosition().getX() - entity.getTransform().getSize())
            return false;
        else if (position.getX() - size > entity.getTransform().getPosition().getX() + entity.getTransform().getSize())
            return false;
        else {
            // onCollide(entity);
            return true;
        }
    }

    public void onCollide(Entity entity) {
        System.out.println( getClass().getName() + " Colliding with " + entity.getClass().getName());
    }
}
