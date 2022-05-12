package entities;

import entities.Entity;
import types.*;

public class Projectile extends Entity {

    // private float speed = 0.6f;

    // private types.Transform transform;

    public Projectile(Transform transform) {
        super(transform);
        this.transform = transform;
        setSpeed(6f);
    }

}
