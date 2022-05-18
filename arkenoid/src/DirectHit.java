import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class: DirectHit.
 * <p>
 * a type of level.
 * has some static values that are shared with all DirectHits.
 */
public class DirectHit extends Level {
    //the following fields are static because i don't mind all the direct hit levels will share them.
    //it also solves a constructor problem.
    private static int numOfBalls = 1;
    private static int paddleSpeed = 10;
    private static int paddleWidth = 100;
    private Point blockLocation = new Point(375, 200);
    private int blockWidth = 50;
    private int blockHeight = 50;


    /**
     * constructor.
     * creates a the DirectHit level.
     *
     * @param scoreTrack the scoreTrack field.
     */

    public DirectHit(ScoreTrackingListener scoreTrack) {
        super(numOfBalls, paddleSpeed, paddleWidth, "Direct Hit", scoreTrack);
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<>();
        Ball ball1 = new Ball(new Point(Level.WIDTH / 2, 530), 6, Color.WHITE, hitListeners);
        balls.add(ball1);
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballsVelocity = new ArrayList<>();
        ballsVelocity.add(Velocity.fromAngleAndSpeed(0, Level.BALL_SPEED));
        return ballsVelocity;
    }


    @Override
    protected Sprite initBackground() {
        return new Block(new Point(0, 0),
                Level.WIDTH, Level.HEIGHT, Color.BLACK);
    }

    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<HitListener>();
        blocks.add(new Block(this.blockLocation, this.blockWidth, this.blockHeight,
                Color.RED, hitListeners, super.getScoreTrack()));
        return blocks;
    }

}

