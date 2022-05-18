import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
/**
 *class: EndScreen.
 * <p>
 * the end screen class.
 */
public class EndScreen implements Animation {

    private GameLevel level;
    private GUI gui;
    private int score;
    private boolean stop;


    /**
     * constructor.
     * creates a new starting animation.
     *
     * @param gui   given gui .
     * @param level the level field.
     * @param score the game's score.
     */
    public EndScreen(GameLevel level, GUI gui, Counter score) {
        this.level = level;
        this.gui = gui;
        this.score = score.getValue();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // drawing the text that will be presented
        d.setColor(Color.WHITE);
        if (level.getNumOfBalls().getValue() > 0) {
            d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2 - 100, "YOU WIN!", 30);
        } else {
            d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2 - 100, "GAME OVER.", 30);
        }
        d.drawText(d.getWidth() / 2 - 160, d.getHeight() / 2, "Your Score is: " + score, 30);
        d.drawText(d.getWidth() / 2 - 150, d.getHeight() / 2 + 200, "press SPACE to close", 30);
        gui.show(d);
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}