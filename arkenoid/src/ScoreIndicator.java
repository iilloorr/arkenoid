import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class: ScoreIndicator.
 * <p>
 * a sprite class, shows the score
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor: ScoreIndicator.
     * <p>
     * a sprite class, shows the score
     * @param score the score field.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(380, 12, "score: " + score.getValue(), 20);
    }

    @Override
    public void timePassed() {
    }
}
