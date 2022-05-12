package types;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time {

    private float cooldown = 2f;
    private float time = 0f;

    public Time() {

    }


    public void waitForSeconds(float seconds) {

    }

    public void updateTimer() {
        time += 0.1;
    }


}