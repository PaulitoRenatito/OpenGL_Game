package entities;

import main.Game;
import managers.GameManager;
import types.Transform;
import types.Weapon;

import java.util.Random;

public class Boss extends Enemy {

    public Boss(Transform transform, int health) {
        super(transform, health);
        weapon = new Weapon(false, 5f, 5);
        weapon.setProjectileSize(60);
    }

    public void updateMovement(float speed,float zigzagSpeed) {
        float switchTime = (GameManager.getWindow().getWidth() * 8/1600f);
        this.getTransform().MoveDownZigZagging(speed, zigzagSpeed, switchTime);
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
