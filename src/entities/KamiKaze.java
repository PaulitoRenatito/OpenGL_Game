package entities;

import managers.GameManager;
import types.Transform;

public class KamiKaze extends Enemy{

    public KamiKaze(Transform transform, int health) {
        super(transform, health);
    }

    public void updateMovement() {
        this.getTransform().moveTowards(GameManager.getPlayer().transform.getPosition());
    }

}
