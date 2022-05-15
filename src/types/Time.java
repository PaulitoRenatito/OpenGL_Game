package types;

import javax.swing.*;
import java.util.TimerTask;

public class Time {
    private float time = 0f;

    public Time() {

    }


    public boolean waitForSeconds(float seconds) {

        if(time > seconds) {
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