public class Vector2 {

    private float x = 0;
    private float y = 0;

    public Vector2() {
        x = 0;
        y = 0;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void incrementX(float x) {
        this.x += x;
    }

    public float getY() {
        return y;
    }

    public void incrementY(float y) {
        this.y += y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
