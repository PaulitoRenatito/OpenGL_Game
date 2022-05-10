public class Projectile {

    private float speed = 0.02f;

    private Movement movement;
    private Vector2 vector2 = new Vector2();

    public Projectile(float startPositionX, float startPositionY) {
        movement = new Movement(0.05f, startPositionX, startPositionY);
    }

    public void updateProjectile() {
        movement.MoveUp(vector2);
    }

}
