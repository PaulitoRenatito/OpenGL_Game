package entities;

import managers.GameManager;
import types.*;

import java.util.Random;

public class Enemy extends Creature {

    protected Weapon weapon = new Weapon(false, 5f);

    public Enemy(Transform transform, int health) {
        super(transform, health);
    }

    public void updateMovement() {
        float switchTime = (GameManager.getWindow().getWidth() * 4/1600f);
        this.getTransform().MoveDownZigZagging(0.3f, switchTime);
    }

    public void updateShooting(int bound) {

        Random random = new Random();

        if (random.nextInt(0, bound) == 0 && weapon.canFire()) {
            weapon.fireProjectile(transform);
        }
        else if(weapon.canFire()) {
            weapon.setTime(0);
        }

        weapon.updateTime();

    }

}
