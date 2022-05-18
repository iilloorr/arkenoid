/**
 * class: Counter.
 * <p>
 * simple class that counts.
 */
public class Counter {
    private int count = 0;

    /**
     * constructor: Counter.
     * <p>
     *
     * @param count the starting count.
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * method: increase.
     * <p>
     *
     * @param number the number to increase the counter.
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * method: decrease.
     * <p>
     *
     * @param number the number to decrease the counter.
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * method: decrease.
     * <p>
     *
     * @return the count field.
     */
    int getValue() {
        return count;
    }
}