
import java.util.List;
/**
 * abstract class: Level.
 * <p>
 * a class to hold information on the level.
 */
public abstract class Level implements LevelInformation {
    private int numOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numOfBlocks;
    private ScoreTrackingListener scoreTrack;
    //some constants all levels are sharing (currently)
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int BALL_SPEED = 10;


    /**
     * constructor.
     *
     * @param numOfBalls  number of ball in the level.
     * @param paddleSpeed the speed of the paddle in the level.
     * @param paddleWidth the width of the paddle in the level.
     * @param levelName   the name of the level.
     * @param scoreTrack  the score track field
     */

    public Level(int numOfBalls, int paddleSpeed, int paddleWidth, String levelName, ScoreTrackingListener scoreTrack) {
        this.scoreTrack = scoreTrack;
        this.numOfBalls = numOfBalls;
        this.ballsVelocities = initialBallVelocities();
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = initBackground();
        this.blocks = blocks();
        this.numOfBlocks = blocks.size();

    }

    @Override
    public abstract List<Ball> balls();

    @Override
    public abstract List<Velocity> initialBallVelocities();

    /**
     * Method: initBackground.
     * the backgrounds will be 1 color.
     *
     * @return returns the background sprite.
     */
    protected abstract Sprite initBackground();


    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }


    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public abstract List<Block> blocks();

    @Override
    public int numberOfBlocksToRemove() {
        return numOfBlocks;
    }

    @Override
    public ScoreTrackingListener getScoreTrack() {
        return scoreTrack;
    }

    @Override
    public int getNumOfBalls() {
        return numOfBalls;
    }

}