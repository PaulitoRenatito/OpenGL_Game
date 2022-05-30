package entities;
import types.*;

public class Projectile extends Entity {

    private boolean isFromPlayer;

    public Projectile(Transform transform, float speed, boolean isFromPlayer) {
        super(transform);
        this.transform = transform;
        this.isFromPlayer = isFromPlayer;
        setSpeed(speed);
    }

    public void updateMovement() {
        if (isFromPlayer) {
            getTransform().MoveUp(getSpeed());
        }
        else {
            getTransform().MoveDown(getSpeed());
        }
    }

    public boolean isFromPlayer() {
        return isFromPlayer;
    }

    public void setFromPlayer(boolean fromPlayer) {
        isFromPlayer = fromPlayer;
    }
}
