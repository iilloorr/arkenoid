import biuoop.DrawSurface;
/**
 * interface: Animation.
 * <p>
 * the animation interface
 */
public interface Animation {
    /**
     * Method: doOneFrame.
     * <p>
     * what should the animation do every frame.
     * this is the engine of the animation
     *
     * @param d the drawSurface of the animation
     */
    void doOneFrame(DrawSurface d);
    /**
     * Method: shouldStop.
     * <p>
     *
     * @return true if the animation should stop, false otherwise.
     */
    boolean shouldStop();
}