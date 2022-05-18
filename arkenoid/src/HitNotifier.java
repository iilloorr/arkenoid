/**
 * interface: HitNotifier.
 * <p>
 * defines the interface of hit notifiers.
 */
public interface HitNotifier {
    /**
     * method: addHitListener.
     * <p>
     * adds a listener to the listeners list.
     *
     * @param hl the listener to be added.
     */
    void addHitListener(HitListener hl);

    /**
     * method: removeHitListener.
     * <p>
     * removes a listener from the listeners list.
     *
     * @param hl the listener to be removed.
     */
    void removeHitListener(HitListener hl);
}