package managers;
import entities.Creature;
import entities.Enemy;
import entities.Projectile;
import types.Transform;
import types.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class LevelManager {

    private int level = 1;

    private int bossLevel = 0;

    private int numberOfEnemies = 4;

    public static ArrayList<Enemy> enemies = new ArrayList<>();

    public static ArrayList<Projectile> projectiles = new ArrayList<>();

    public LevelManager() {

    }

    public void updateLevel() {

        if(enemies.size() == 0) {
            spawnEnemies();
            level++;
        }

        checkCollisions();

        updateEnemiesMovement();

        updateProjectilesMovement();

        limitBounds();

    }

    private void spawnEnemies() {

        if (level % 5 == 0) {
            Enemy enemy = new Enemy(new Transform(60f,
                    new Vector2(0f, 1000f), new Color(1, 0, 0, 1)), 5 * (level/5));
            enemies.add(enemy);

            bossLevel++;
            numberOfEnemies = 4;
        }
        else {

            numberOfEnemies += 2;

            float spaceBetweenEnemies = GameManager.getScreen().getWidth()/(numberOfEnemies-2.5f);

            if (bossLevel > 0) {

                boolean startMovingRight = false;

                for (int j = 0; j <= bossLevel; j++) {
                    startMovingRight = !startMovingRight;
                    for (int i = 1; i <= numberOfEnemies; i++) {
                        Enemy enemy;
                        if (i > (numberOfEnemies/2f)) {
                            enemy = new Enemy(new Transform(60f,
                                    new Vector2(spaceBetweenEnemies * ((i - 0.5f) - (numberOfEnemies/2f)), 1000f + (200f * j)),
                                    new Color(1, 0, 0, 1)), 1);
                            enemy.getTransform().setStartMovingRight(startMovingRight);
                        }
                        else {
                            enemy = new Enemy(new Transform(60f,
                                    new Vector2(spaceBetweenEnemies * (i - 0.5f) * -1, 1000f  + (200f * j)),
                                    new Color(1, 0, 0, 1)), 1);
                            enemy.getTransform().setStartMovingRight(startMovingRight);
                        }

                        enemies.add(enemy);
                    }
                }
            }
            else {
                for (int i = 1; i <= numberOfEnemies; i++) {
                    Enemy enemy;
                    if (i > (numberOfEnemies/2f)) {
                        enemy = new Enemy(new Transform(60f,
                                new Vector2(spaceBetweenEnemies * (i - (numberOfEnemies/2f)), 800f + (100f * bossLevel)),
                                new Color(1, 0, 0, 1)), 1);
                    }
                    else {
                        enemy = new Enemy(new Transform(60f,
                                new Vector2(spaceBetweenEnemies * i * -1, 800f  + (100f * bossLevel)),
                                new Color(1, 0, 0, 1)), 1);
                    }

                    enemies.add(enemy);
                }
            }
        }
    }

    private void checkCollisions() {

        Projectile removeProjectile = null;
        Enemy removeEnemy = null;

        for (Projectile projectile : projectiles) {
            for (Enemy enemy : enemies) {
                if (projectile.getTransform().getCollider().collidingWith(enemy)) {

                    enemy.receiveDamage(1);

                    if (!enemy.isAlive()) {
                        removeEnemy = enemy;
                    }

                    removeProjectile = projectile;

                }
            }
        }

        if(removeProjectile != null) {
            projectiles.remove(removeProjectile);
        }

        if (removeEnemy != null) {
            enemies.remove(removeEnemy);
        }

        for (Enemy enemy : enemies) {
            if(GameManager.getPlayer().getTransform().getCollider().collidingWith(enemy)) {
                GameManager.getPlayer().receiveDamage(1);
            }
        }

    }

    private void updateEnemiesMovement() {
        if ((level-1) % 5 == 0) {
            for (Enemy enemy : enemies) {
                enemy.getTransform().moveTowards(GameManager.getPlayer().getTransform().getPosition());
            }
        }
        else {
            for (Enemy enemy : enemies) {
                enemy.updateMovement();
            }
        }
    }

    private void updateProjectilesMovement() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            projectile.updateMovement();
            if (isOutOfScreen(projectile)) {
                projectiles.remove(projectile);
            }
        }
    }

    private boolean isOutOfScreen(Projectile projectile) {

        int boundY = GameManager.getScreen().getHeight();

        if (projectile.getTransform().getPosition().getY() + projectile.getTransform().getSize() > boundY ||
                projectile.getTransform().getPosition().getY() - projectile.getTransform().getSize() < -boundY) {
            return true;
        }
        else {
            return false;
        }
    }

    public void limitBounds() {

        int boundX = GameManager.getScreen().getWidth();
        int boundY = GameManager.getScreen().getHeight();

        Vector2 playerPosition = GameManager.getPlayer().getTransform().getPosition();
        float playerSize = GameManager.getPlayer().getTransform().getSize();

        if(playerPosition.getX() + playerSize >= boundX - 20f){
            playerPosition.setX(boundX - 20f - playerSize);
        }else if(playerPosition.getX() - GameManager.getPlayer().getTransform().getSize() <= -boundX + 20f){
            playerPosition.setX(-boundX + 20f + playerSize);
        }

        if(playerPosition.getY() + playerSize >= boundY - 20f){
            playerPosition.setY(boundY - 20f - playerSize);
        }else if(playerPosition.getY() - playerSize <= -boundY + 20f){
            playerPosition.setY(-boundY + 20f + playerSize);
        }

    }

}
