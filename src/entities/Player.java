package entities;
import gui.Sound;
import types.*;

public class Player extends Creature {

    Weapon weapon = new Weapon(true);

    public Player(int health) {
        super(health);
        setSpeed(12f);
    }

    public void getInputs() {

        int movementX = 0;
        int movementY = 0;

        if (Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.ARROW_LEFT)) {
            movementX = 1;
            transform.getPosition().incrementX(-getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }
        else if (Input.GetKey(KeyCode.D) || Input.GetKey(KeyCode.ARROW_RIGHT)) {
            movementX = 1;
            transform.getPosition().incrementX(getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }
        if (Input.GetKey(KeyCode.S) || Input.GetKey(KeyCode.ARROW_DOWN))  {
            movementY = 1;
            transform.getPosition().incrementY(-getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }
        else if (Input.GetKey(KeyCode.W) || Input.GetKey(KeyCode.ARROW_UP)) {
            movementY = 1;
            transform.getPosition().incrementY(getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }

        if ((Input.GetMouseButton(KeyCode.LEFT_BUTTON) || Input.GetKey(KeyCode.SPACE)) && weapon.canFire()) {
            Sound sound = new Sound();
            sound.setFile("res/shoot.wav");
            sound.play();
            weapon.fireProjectile(transform);
        }

        weapon.updateTime();

        transform.updateMovement(transform.getPosition());
    }

}
