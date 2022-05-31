package entities;
import types.*;

public class Projectile extends Entity {

    private boolean isFromPlayer;

    private int damage = 1;

    public Projectile(Transform transform, float speed, int damage, boolean isFromPlayer) {
        super(transform);
        this.transform = transform;
        this.isFromPlayer = isFromPlayer;
        this.damage = damage;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
