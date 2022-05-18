/**
 * class: ScoreTrackingListener.
 * a score tracking hit listener
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor: ScoreTrackingListener.
     * a score tracking hit listener
     *
     * @param scoreCounter the field scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * Method: getCurrentScore.
     *
     * @return the currentScore field.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}