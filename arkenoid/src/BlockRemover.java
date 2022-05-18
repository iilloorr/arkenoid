/**
 * class: BlockRemover.
 * <p>
 * hit listener that let us know how much block we removed.
 * if we have no blocks the game will close (player won)- implemented in game class.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter numOfBlocks;

    /**
     * constructor: BlockRemover.
     * <p>
     * hit listener that let us know how much blocks we have.
     * if we have no blocks the game will close (player won)- implemented in game class.
     *  @param game          the game where we count the number of blocks.
     * @param removedBlocks the number of blocks we removed.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.numOfBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.numOfBlocks.decrease(1);
        beingHit.removeFromGame(this.game);
    }
}