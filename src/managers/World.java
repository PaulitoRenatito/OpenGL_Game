package managers;

import entities.Creature;
import entities.Entity;
import entities.Player;
import entities.Projectile;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class World {

    Player player;
    Screen screen;

    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<Creature> creatures = new ArrayList<>();
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

    public void updateProjectiles() {
        if (projectiles.size() > 0) {
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile projectile = projectiles.get(i);
                projectile.getTransform().MoveUp(
                        projectile.getTransform().getPosition(),
                        projectile.getSpeed());
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
