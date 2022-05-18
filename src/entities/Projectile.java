package entities;
import types.*;

public class Projectile extends Entity {

    private boolean movingUp = true;

    public Projectile(Transform transform, float speed, boolean movingUp) {
        super(transform);
        this.transform = transform;
        this.movingUp = movingUp;
        setSpeed(speed);
    }

    public void updateMovement() {
        if (movingUp) {
            getTransform().MoveUp(getSpeed());
        }
        else {
            getTransform().MoveDown(getSpeed());
        }
    }

}
