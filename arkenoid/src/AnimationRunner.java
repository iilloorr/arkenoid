import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * class: AnimationRunner.
 * <p>
 * a class that runs the animation (with a loop of course)
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     * <p>
     *
     * @param gui             the gui field
     * @param framesPerSecond the framePerSecond field
     * @param sleeper         the sleeper field.
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.sleeper = sleeper;
    }

    /**
     * Method: run.
     * running the animation using a loop.
     *
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Method: getGui.
     * returns the gui of the class
     *
     * @return the gui field.
     */
    public GUI getGui() {
        return this.gui;
    }
}