public class Projectile {

    private float speed = 0.6f;

    private Transform transform;

    public Projectile(float size, Vector2 position) {
        transform = new Transform(size, position);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
