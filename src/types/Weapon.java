package types;

import entities.Projectile;
import managers.LevelManager;

import java.awt.*;

public class Weapon {

    boolean pointingUp;
    float projectileSize = 20f;
    Color projectileColor = new Color(0.6f, 0.6f, 0, 1);

    private float cooldown = 2f;
    private float time = 0f;

    public Weapon(boolean pointingUp) {
        this.pointingUp = pointingUp;
    }

    public Weapon(boolean pointingUp, float cooldown) {
        this.pointingUp = pointingUp;
        this.cooldown = cooldown;
    }

    public void fireProjectile(Transform transform) {

        if(canFire()) {
            Projectile projectile;

            if (pointingUp) {
                projectile = new Projectile(new Transform(projectileSize, new Vector2(transform.getPosition().getX(),
                        transform.getPosition().getY() + transform.getSize()), projectileColor), 5f, true);
            }
            else {
                projectile = new Projectile(new Transform(projectileSize, new Vector2(transform.getPosition().getX(),
                        transform.getPosition().getY() - transform.getSize()), projectileColor), 5f, false);
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

}
