/**
 * interface: HitListener.
 * <p>
 * defines the interface of hit listeners.
 */
public interface HitListener {
    /**
     * method: hitEvent.
     * <p>
     * "tells" the hit listener what to do in case of a hit event on an hit notifier.
     *
     * @param hitter   the ball that hit the hit notifier.
     * @param beingHit the block that was hit by the ball (which is the hit notifier).
     */
    void hitEvent(Block beingHit, Ball hitter);
}