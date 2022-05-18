import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.GUI;

import java.awt.Color;

/**
 * class: PauseScreen.
 * <p>
 * A class of pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private GUI gui;

    /**
     * constructor.
     * creates a new "waiting-for-key-press" behavior.
     *
     * @param k   given keyboard sensor.
     * @param gui given gui.
     */
    public PauseScreen(KeyboardSensor k, GUI gui) {
        this.keyboard = k;
        this.gui = gui;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        gui.show(d);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
