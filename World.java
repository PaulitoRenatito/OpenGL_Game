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
        if(player.getVector2().getX() >= boundX){
            player.getVector2().setX(boundX);
        }else if(player.getVector2().getX() <= -boundX){
            player.getVector2().setX(-boundX);
        }

        if(player.getVector2().getY() >= boundY){
            player.getVector2().setY(boundY);
        }else if(player.getVector2().getY() <= -boundY){
            player.getVector2().setY(-boundY);
        }
    }

}
