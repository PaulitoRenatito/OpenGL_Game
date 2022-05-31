package types;

import entities.Projectile;
import managers.LevelManager;

import java.awt.*;

public class Weapon {

    boolean pointingUp;
    float projectileSize = 20f;

    private float cooldown = 2f;
    private float time = 0f;

    private int damage = 1;

    public Weapon(boolean pointingUp) {
        this.pointingUp = pointingUp;
    }

    public Weapon(boolean pointingUp, float cooldown) {
        this.pointingUp = pointingUp;
        this.cooldown = cooldown;
    }

    public Weapon(boolean pointingUp, float cooldown, int damage) {
        this.pointingUp = pointingUp;
        this.cooldown = cooldown;
        this.damage = damage;
    }

    public void fireProjectile(Transform transform) {

        if(canFire()) {
            Projectile projectile;

            if (pointingUp) {
                projectile = new Projectile(new Transform(projectileSize, new Vector2(transform.getPosition().getX(),
                        transform.getPosition().getY() + transform.getSize()), new Sprite("res/Shoot.png")),
                        5f, damage, true);
            }
            else {
                projectile = new Projectile(new Transform(projectileSize, new Vector2(transform.getPosition().getX(),
                        transform.getPosition().getY() - transform.getSize()), new Sprite("res/Shoot.png")),
                        2.5f, damage, false);
            }


            LevelManager.projectiles.add(projectile);

            time = 0;
        }

    }

    public boolean canFire() {
        return time > cooldown;
    }

    public void updateTime() {
        time += 0.1;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getProjectileSize() {
        return projectileSize;
    }

    public void setProjectileSize(float projectileSize) {
        this.projectileSize = projectileSize;
    }
}
