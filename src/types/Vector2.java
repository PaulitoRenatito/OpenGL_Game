package types;

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

    public Vector2 add(Vector2 vector) {
        Vector2 vectorC = new Vector2();
        vectorC.x = x + vector.x;
        vectorC.y = y + vector.y;
        return vectorC;
    }

    public Vector2 subtract(Vector2 vector) {
        Vector2 vectorResultant = new Vector2();
        vectorResultant.x = x - vector.x;
        vectorResultant.y = y - vector.y;
        return vectorResultant;
    }

    public Vector2 divided(float divisor) {
        Vector2 vectorResultant = new Vector2();
        vectorResultant.x = x / divisor;
        vectorResultant.y = y / divisor;
        return vectorResultant;
    }

    public Vector2 multiply(float scalar) {
        Vector2 vectorResultant = new Vector2();
        vectorResultant.x = x * scalar;
        vectorResultant.y = y * scalar;
        return vectorResultant;
    }

    public void increment(Vector2 vector) {
        x = x + vector.x;
        y = y + vector.y;
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
