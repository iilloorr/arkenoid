import java.util.List;
/**
 * interface: LevelInformation.
 * <p>
 * classes that implement that interface should hold
 * information about levels.
 */
public interface LevelInformation {
    /**
     * Method: numberOfBalls.
     *
     * @return returns the number of balls.
     */
    int numberOfBalls();

    /**
     * Method: balls.
     * creates a list of balls.
     *
     * @return a list with the balls.
     */
    List<Ball> balls();

    /**
     * Method: initialBallVelocities.
     * creates a list of velocities, one for each of our balls.
     *
     * @return a list with the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Method: paddleSpeed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * Method: paddleWidth.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * Method: levelName.
     *
     * @return the level's name.
     */
    String levelName();

    /**
     * Method: getBackground.
     *
     * @return the background.
     */
    Sprite getBackground();

    /**
     * Method: blocks.
     * method for creating the level's blocks.
     *
     * @return a list with the level's blocks.
     */
    List<Block> blocks();

    /**
     * Method: numberOfBlocksToRemove.
     *
     * @return the number of blocks left on the stage.
     */
    int numberOfBlocksToRemove();

    /**
     * Method: getScoreTrack.
     *
     * @return the scoreTrack.
     */
    ScoreTrackingListener getScoreTrack();

    /**
     * Method: getNumOfBalls.
     *
     * @return the number of balls left.
     */
    int getNumOfBalls();

}