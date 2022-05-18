package entities;

import types.*;

public class Enemy extends Creature {

    private int switchSide;

    public Enemy(Transform transform, int health) {
        super(transform, health);
    }

    public void updateMovement() {
        this.getTransform().MoveDownZigZagging(0.3f, 3);
    }

}
