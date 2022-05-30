package entities;

import types.*;

import java.util.Random;

public class Enemy extends Creature {

    private Weapon weapon = new Weapon(false, 5f);

    public Enemy(Transform transform, int health) {
        super(transform, health);
    }

    public void updateMovement() {
        this.getTransform().MoveDownZigZagging(0.3f, 3);
    }

    public void updateMovement(float speed,float zigzagSpeed, int switchSideTime) {
        this.getTransform().MoveDownZigZagging(speed, zigzagSpeed, switchSideTime);
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
