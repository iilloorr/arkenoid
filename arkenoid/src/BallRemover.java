/**
 * class: BallRemover.
 * <p>
 * hit listener that let us know how much balls we removed.
 * if we have no balls the game will close (player lost)- implemented in game class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    /**
     * constructor: BallRemover.
     * <p>
     * hit listener that let us know how much balls we have.
     * if we have no balls the game will close (player lost)- implemented in game class.
     *
     * @param game         the game where we count the number of balls
     * @param removedBalls the number of balls we removed.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.removedBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }
}
