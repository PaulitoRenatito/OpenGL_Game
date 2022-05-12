package entities;

import entities.Entity;


import types.*;

public abstract class Creature extends Entity {

    private int health;
    private boolean isAlive = true;

    public Creature(int health) {
        super();
        this.health = health;
    }

    public Creature(Transform transform, int health) {
        super(transform);
        this.health = health;
    }

    public void receiveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0) {
            this.health = 0;
            setAlive(false);
        }
        else {
            this.health = health;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
