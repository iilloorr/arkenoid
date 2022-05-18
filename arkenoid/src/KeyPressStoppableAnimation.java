import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * class: KeyPressStoppableAnimation.
 * <p>
 * A class to wait for key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * constructor.
     * creates a new "waiting-for-key-press" behavior.
     *
     * @param keyboard  the keyboard field.
     * @param key       the key to press.
     * @param animation the animation field.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.keyboard = keyboard;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboard.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!keyboard.isPressed(key)) {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}