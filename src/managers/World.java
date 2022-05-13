package managers;

import entities.*;
import types.Transform;
import types.Vector2;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class World {

    Player player;
    Screen screen;

    //public static ArrayList<Entity> entities = new ArrayList<>();

    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Projectile> projectiles = new ArrayList<>();

    private float boundX;

    private float boundY;

    public World(Player player, Screen screen) {
        this.player = player;
        this.screen = screen;

        glViewport(0, 0, screen.getWidth(), screen.getHeight());
        glMatrixMode(GL_PROJECTION);
        glOrtho(-screen.getWidth(),
                screen.getWidth(),
                -screen.getHeight(), screen.getHeight(),
                -1, 1);

        boundX = screen.getWidth();

        boundY = screen.getHeight();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

    }

    public void spawnEnemies() {

        float spaceBetweenEnemies = screen.getWidth()/4f;
        int numberOfEnemies = 6;

        for (int i = 1; i <= numberOfEnemies; i++) {
            Enemy enemy;
            if (i > 3) {
                enemy = new Enemy(new Transform(60f,
                        new Vector2(spaceBetweenEnemies * (i - 3), 800f), new Color(1, 0, 0, 1)), 1);
            }
            else {
                enemy = new Enemy(new Transform(60f,
                        new Vector2(spaceBetweenEnemies * i * -1, 800f), new Color(1, 0, 0, 1)), 1);
            }
            enemies.add(enemy);
        }

    }

    public void checkCollision() {

        if (projectiles.size() > 0) {
            for (Projectile projectile : projectiles) {
                for (Enemy enemy : enemies) {
                    if(projectile.getTransform().getCollider().collidingWith(enemy))
                    {
                        enemy.receiveDamage(1);
                    }
                }
            }
        }

        for (Enemy enemy : enemies) {
            if(player.getTransform().getCollider().collidingWith(enemy)) {
                enemy.receiveDamage(5);
            }
        }
    }

    public void updateEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            if (!(enemies.get(i).isAlive())) {
                enemies.remove(enemies.get(i));
            }

            if (enemies.size() > 0) {
                enemies.get(i).updateMovement();
            }
        }
    }

    public void updateProjectiles() {
        if (projectiles.size() > 0) {
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile projectile = projectiles.get(i);
                projectile.getTransform().MoveUp(projectile.getSpeed());
                if (isOutOfScreen(projectile)) {
                    projectiles.remove(projectile);
                }
            }
        }
    }

    private boolean isOutOfScreen(Entity entity) {
        if (entity.getTransform().getPosition().getY() + entity.getTransform().getSize() > boundY ||
                entity.getTransform().getPosition().getY() - entity.getTransform().getSize() < -boundY) {
            return true;
        }
        else {
            return false;
        }
    }

    public void limitBounds() {

        if(player.getTransform().getPosition().getX() + player.getTransform().getSize() >= boundX - 20f){
            player.getTransform().getPosition().setX(boundX - 20f - player.getTransform().getSize());
        }else if(player.getTransform().getPosition().getX() - player.getTransform().getSize() <= -boundX + 20f){
            player.getTransform().getPosition().setX(-boundX + 20f + player.getTransform().getSize());
        }

        if(player.getTransform().getPosition().getY() + player.getTransform().getSize() >= boundY - 20f){
            player.getTransform().getPosition().setY(boundY - 20f - player.getTransform().getSize());
        }else if(player.getTransform().getPosition().getY() - player.getTransform().getSize() <= -boundY + 20f){
            player.getTransform().getPosition().setY(-boundY + 20f + player.getTransform().getSize());
        }
    }

}
