package entities;

import types.*;

public class Enemy extends Creature {

    private Vector2 vector2 = new Vector2();

    public Enemy(Transform transform, int health) {
        super(transform, health);
    }

    public void updateMovement() {
        this.getTransform().MoveDown(
                vector2, 0.5f);
    }

}
