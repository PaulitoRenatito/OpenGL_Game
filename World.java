import static org.lwjgl.opengl.GL11.*;

public class World {

    Player player;
    Screen screen;

    private float boundX;

    private float boundY;

    public World(Player player, Screen screen) {
        this.player = player;
        this.screen = screen;

        glViewport(0, 0, screen.getWidth(), screen.getHeight());
        glMatrixMode(GL_PROJECTION);
        glOrtho(-screen.getAspect(), screen.getAspect(), -1, 1, -1, 1);

        boundX = screen.getAspect();

        boundY = 1;

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

    }

    public void limitBounds() {
        if(player.getTransform().getPosition().getX() + player.getTransform().getSize() >= boundX){
            player.getTransform().getPosition().setX(boundX - player.getTransform().getSize());
        }else if(player.getTransform().getPosition().getX() - player.getTransform().getSize() <= -boundX){
            player.getTransform().getPosition().setX(-boundX + player.getTransform().getSize());
        }

        if(player.getTransform().getPosition().getY() + player.getTransform().getSize() >= boundY){
            player.getTransform().getPosition().setY(boundY - player.getTransform().getSize());
        }else if(player.getTransform().getPosition().getY() - player.getTransform().getSize() <= -boundY){
            player.getTransform().getPosition().setY(-boundY + player.getTransform().getSize());
        }
    }

}
