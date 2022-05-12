package entities;

import types.*;

public abstract class Entity {

    protected Transform transform;
    private float speed = 20f;

    public Entity() {
        transform = new Transform();
    }

    public Entity(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
