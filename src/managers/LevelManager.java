package managers;
import entities.*;
import gui.Sound;
import main.Game;
import types.GameState;
import types.Sprite;
import types.Transform;
import types.Vector2;
import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

public class LevelManager {

    Sound music = new Sound("res/music.wav");

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

        if (!music.isPlaying()) {
            music.play();
        }

    }

    private void updateBackground() {

        sprite.enableImage();

        glBindTexture(GL_TEXTURE_2D, sprite.getId());

        glColor3f(1, 1, 1);

        glBegin(GL_QUADS);

        double width = 1920f / GameManager.getWindow().getNewWidth();
        double height = 1080f / GameManager.getWindow().getNewHeight();

        glTexCoord2f(0, 0);
        glVertex3d(-width, -height, -1);

        glTexCoord2f(1, 0);
        glVertex3d(width, -height, -1);

        glTexCoord2f(1, 1);
        glVertex3d(width, height, -1);

        glTexCoord2f(0, 1);
        glVertex3d(-width, height, -1);

        glEnd();

        sprite.disableImage();
    }

    private void spawnEnemies() {

        float spawnY = GameManager.getWindow().getHeight();

        if (level % 5 == 0) {
            Enemy enemy = new Boss(new Transform(200f,
                    new Vector2(-(GameManager.getWindow().getHeight()*1000/1600f), spawnY), new Sprite("res/ship3.png")), 10 * (level/5));
            enemies.add(enemy);

            bossLevel++;
            numberOfEnemies = 4;
        }
        else {

            numberOfEnemies += 2;

            float spaceBetweenEnemies = GameManager.getWindow().getWidth()/(numberOfEnemies-2.5f);

            Random random = new Random();
            int kamiKaze = random.nextInt(0, 3);

            if (bossLevel > 0) {

                boolean startMovingRight = false;

                for (int j = 0; j <= bossLevel; j++) {
                    startMovingRight = !startMovingRight;
                    for (int i = 1; i <= numberOfEnemies; i++) {
                        Enemy enemy;
                        if (i > (numberOfEnemies/2f)) {
                            enemy = new Enemy(new Transform(60f,
                                    new Vector2(spaceBetweenEnemies * ((i - 0.5f) - (numberOfEnemies/2f)), spawnY + (200f * j)),
                                    new Sprite("res/ship2.png")), 1);
                            enemy.getTransform().setStartMovingRight(startMovingRight);
                        }
                        else {
                            enemy = new Enemy(new Transform(60f,
                                    new Vector2((spaceBetweenEnemies * (i - 0.5f) * -1) - (spaceBetweenEnemies/3.4f), spawnY  + (200f * j)),
                                    new Sprite("res/ship2.png")), 1);
                            enemy.getTransform().setStartMovingRight(startMovingRight);
                        }

                        enemies.add(enemy);
                    }
                }

                for (int i = 0; i < kamiKaze; i ++) {
                    float spaceBetweenEnemies2 = GameManager.getWindow().getWidth()/3f;
                    Enemy enemy = new KamiKaze(new Transform(60f,
                            new Vector2(spaceBetweenEnemies2 * i, spawnY * 2),
                            new Sprite("res/ship4.png")), 2);
                    enemies.add(enemy);
                }

            }
            else {
                for (int i = 1; i <= numberOfEnemies; i++) {
                    Enemy enemy;
                    if (i > (numberOfEnemies/2f)) {
                        enemy = new Enemy(new Transform(60f,
                                new Vector2(spaceBetweenEnemies * (i - (numberOfEnemies/2f)), spawnY + (100f * bossLevel)),
                                new Sprite("res/ship2.png")), 1);
                    }
                    else {
                        enemy = new Enemy(new Transform(60f,
                                new Vector2((spaceBetweenEnemies * i * -1) - (spaceBetweenEnemies/3.4f), spawnY  + (100f * bossLevel)),
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

            if (projectile.isFromPlayer()) {
                for (Enemy enemy : enemies) {
                    if (projectile.getTransform().getCollider().collidingWith(enemy)) {

                        enemy.receiveDamage(projectile.getDamage());

                        if (enemy instanceof Boss boss) {
                            boss.getTransform().getCollider().onCollide(boss.getTransform());
                        }
                        else if(enemy instanceof KamiKaze kamiKaze) {
                            kamiKaze.getTransform().getCollider().onCollide(kamiKaze.getTransform());
                        }

                        if (!enemy.isAlive()) {
                            removeEnemy = enemy;
                        }

                        removeProjectile = projectile;

                    }
                }
            }
            else {
                if (projectile.getTransform().getCollider().collidingWith(GameManager.getPlayer())) {

                    GameManager.getPlayer().receiveDamage(projectile.getDamage());
                    GameManager.getPlayer().getTransform().getCollider().onCollide(GameManager.getPlayer().getTransform());

                    Sound damageSound = new Sound("res/damage.wav");

                    if (!damageSound.isPlaying()) {
                        damageSound.play();
                    }

                    if (!GameManager.getPlayer().isAlive()) {
                        Game.GAME_STATE = GameState.GAME_OVER;
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
            Sound explosionSound = new Sound("res/explosion.wav");
            explosionSound.play();
        }

        for (Enemy enemy : enemies) {
            if(GameManager.getPlayer().getTransform().getCollider().collidingWith(enemy)) {

                Sound damageSound = new Sound("res/damage.wav");

                damageSound.play();

                if (enemy instanceof Boss || enemy instanceof KamiKaze) {
                    GameManager.getPlayer().receiveDamage(5);
                }
                else {
                    GameManager.getPlayer().receiveDamage(1);
                    GameManager.getPlayer().getTransform().getCollider().onCollide(GameManager.getPlayer().getTransform());
                }

                if (!GameManager.getPlayer().isAlive()) {
                    music.stop();
                    Game.GAME_STATE = GameState.GAME_OVER;
                }

                enemy.receiveDamage(1);

                if (!enemy.isAlive()) {
                    removeEnemy = enemy;
                }

            }
        }

        if(removeProjectile != null) {
            projectiles.remove(removeProjectile);
        }

        if (removeEnemy != null) {
            enemies.remove(removeEnemy);

            Sound explosionSound = new Sound("res/explosion.wav");
            explosionSound.play();
        }

    }

    private void updateEnemiesMovement() {
        if ((level-1) % 5 == 0) {
            for (Enemy enemy : enemies) {
                Boss boss = (Boss) enemy;
                boss.updateMovement(0.2f, 2f );
                boss.updateShooting(3);
            }
        }
        else {
            for (Enemy enemy : enemies) {
                if (enemy instanceof KamiKaze kamiKaze) {
                    kamiKaze.updateMovement();
                }
                else {
                    enemy.updateMovement();
                    enemy.updateShooting(10 + (bossLevel * 4));
                }

                if (GameManager.getWindow().isBelowTheScreen(enemy)) {
                    music.stop();
                    Game.GAME_STATE = GameState.GAME_OVER;
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
