package managers;
import entities.Enemy;
import entities.Player;
import entities.Projectile;
import types.Sprite;
import types.Transform;
import types.Vector2;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

public class LevelManager {

    private int level = 1;

    private int bossLevel = 0;

    private int numberOfEnemies = 4;

    public static ArrayList<Enemy> enemies = new ArrayList<>();

    public static ArrayList<Projectile> projectiles = new ArrayList<>();

    private Sprite sprite = new Sprite("res/background.png");

    public LevelManager() {

    }

    public void updateLevel() {

        updateBackground();

        if (GameManager.getPlayer() == null) {
            GameManager.setPlayer(new Player(5));
        }

        GameManager.getPlayer().getInputs();

        if(enemies.size() == 0) {
            spawnEnemies();
            level++;
        }

        checkCollisions();

        updateEnemiesMovement();

        updateProjectilesMovement();

        limitBounds();

    }

    private void updateBackground() {

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glColor3f(1, 1, 1);

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex3f(-GameManager.getWindow().getWidth(), -GameManager.getWindow().getHeight(), -1);

        glTexCoord2f(1, 0);
        glVertex3f(GameManager.getWindow().getWidth(), -GameManager.getWindow().getHeight(), -1);

        glTexCoord2f(1, 1);
        glVertex3f(GameManager.getWindow().getWidth(), GameManager.getWindow().getHeight(), -1);

        glTexCoord2f(0, 1);
        glVertex3f(-GameManager.getWindow().getWidth(), GameManager.getWindow().getHeight(), -1);

        glEnd();

        sprite.disableImage();
    }

    private void spawnEnemies() {

        if (level % 5 == 0) {
            Enemy enemy = new Enemy(new Transform(60f,
                    new Vector2(0f, 1000f), new Sprite("res/ship3.png")), 5 * (level/5));
            enemies.add(enemy);

            bossLevel++;
            numberOfEnemies = 4;
        }
        else {

            numberOfEnemies += 2;

            float spaceBetweenEnemies = GameManager.getWindow().getWidth()/(numberOfEnemies-2.5f);

            if (bossLevel > 0) {

                boolean startMovingRight = false;

                for (int j = 0; j <= bossLevel; j++) {
                    startMovingRight = !startMovingRight;
                    for (int i = 1; i <= numberOfEnemies; i++) {
                        Enemy enemy;
                        if (i > (numberOfEnemies/2f)) {
                            enemy = new Enemy(new Transform(60f,
                                    new Vector2(spaceBetweenEnemies * ((i - 0.5f) - (numberOfEnemies/2f)), 1000f + (200f * j)),
                                    new Sprite("res/ship2.png")), 1);
                            enemy.getTransform().setStartMovingRight(startMovingRight);
                        }
                        else {
                            enemy = new Enemy(new Transform(60f,
                                    new Vector2(spaceBetweenEnemies * (i - 0.5f) * -1, 1000f  + (200f * j)),
                                    new Sprite("res/ship2.png")), 1);
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
                                new Sprite("res/ship2.png")), 1);
                    }
                    else {
                        enemy = new Enemy(new Transform(60f,
                                new Vector2(spaceBetweenEnemies * i * -1, 800f  + (100f * bossLevel)),
                                new Sprite("res/ship2.png")), 1);
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
                enemy.updateMovement(0.3f, 0.6f , 5);
            }
        }
        else {
            for (Enemy enemy : enemies) {
                enemy.updateMovement();
                if (GameManager.getWindow().isBelowTheScreen(enemy)) {
                    GameManager.gameOver = true;
                }
            }
        }
    }

    private void updateProjectilesMovement() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            projectile.updateMovement();
            if (GameManager.getWindow().isOutOfScreen(projectile)) {
                projectiles.remove(projectile);
            }
        }
    }

    public void limitBounds() {

        int boundX = GameManager.getWindow().getWidth();
        int boundY = GameManager.getWindow().getHeight();

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
