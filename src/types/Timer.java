package types;

public class Timer {

    private float cooldown;
    private double time = 0;

    public Timer(float cooldown) {
        this.cooldown = cooldown;
    }

    public boolean cooldownReached() {
        if (time > cooldown) {
            time = 0;
            return true;
        }
        else {
            return false;
        }
    }

    public void updateTimer() {
        time += 0.1;
    }


}