package types;
import static org.lwjgl.glfw.GLFW.*;

public enum KeyCode {

    A(GLFW_KEY_A),
    S(GLFW_KEY_S),
    D(GLFW_KEY_D),
    W(GLFW_KEY_W),
    ARROW_LEFT(GLFW_KEY_LEFT),
    ARROW_DOWN(GLFW_KEY_DOWN),
    ARROW_RIGHT(GLFW_KEY_RIGHT),
    ARROW_UP(GLFW_KEY_UP),
    P(GLFW_KEY_P),
    R(GLFW_KEY_R),
    ESC(GLFW_KEY_ESCAPE),
    SPACE(GLFW_KEY_SPACE),
    LEFT_BUTTON(GLFW_MOUSE_BUTTON_1),
    RIGHT_BUTTON(GLFW_MOUSE_BUTTON_2);

    private final int value;

    KeyCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
