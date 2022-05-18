package entities;

import managers.*;
import types.*;

import java.awt.*;

public class Player extends Creature {

    Weapon weapon = new Weapon(true);

    public Player(int health) {
        super(health);
        setSpeed(12f);
    }

    public void getInputs() {

        int movementX = 0;
        int movementY = 0;

        if (Input.GetKey(KeyCode.A) ) {
            movementX = 1;
            transform.getPosition().incrementX(-getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }
        else if (Input.GetKey(KeyCode.D)) {
            movementX = 1;
            transform.getPosition().incrementX(getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }
        if (Input.GetKey(KeyCode.S))  {
            movementY = 1;
            transform.getPosition().incrementY(-getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }
        else if (Input.GetKey(KeyCode.W)) {
            movementY = 1;
            transform.getPosition().incrementY(getSpeed()/(movementX + movementY));
            transform.updateMovement(transform.getPosition());
        }

        if ((Input.GetMouseButton(KeyCode.LEFT_BUTTON) || Input.GetKey(KeyCode.SPACE)) && weapon.canFire()) {
            weapon.fireProjectile(transform);
        }

        weapon.updateTime();

        transform.updateMovement(transform.getPosition());
    }

}
