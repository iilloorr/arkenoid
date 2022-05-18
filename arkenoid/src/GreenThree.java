import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class: GreenThree.
 * <p>
 * a type of level.
 * has some static values that are shared with all GreenThrees.
 */
public class GreenThree extends Level {
    //the following fields are static because i don't mind all the direct hit levels will share them.
    //it also solves a constructor problem.
    private static int numOfBalls = 2;
    private static int paddleSpeed = 10;
    private static int paddleWidth = 100;
    private static Point blockLocation = new Point(24, 200);
    private static int blockWidth = 47;
    private static int blockHeight = 20;


    /**
     * constructor.
     * creates a the GreenThree level.
     *
     * @param scoreTrack the scoreTrack field.
     */
    public GreenThree(ScoreTrackingListener scoreTrack) {
        super(numOfBalls, paddleSpeed, paddleWidth, "Green 3", scoreTrack);
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Ball ball = new Ball(new Point(370 + i * 50, 560 - i * 10), 6, Color.WHITE, hitListeners);
            balls.add(ball);
        }
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(-80 + i * 20, Level.BALL_SPEED));
        }
        return ballsVelocity;
    }


    @Override
    protected Sprite initBackground() {
        return new Block(new Point(0, 0),
                Level.WIDTH, Level.HEIGHT, new Color(0, 150, 0));
    }

    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<HitListener>();
        int upperX = WIDTH - blockWidth - 24;
        int upperY = (int) blockLocation.getY();
        for (int i = 0; i < 12; i++) {
            Block block = new Block(new Point(upperX, upperY), blockWidth, blockHeight,
                    Color.gray, hitListeners, super.getScoreTrack());
            upperX -= 47;
            blocks.add(block);
        }
        //creating the second line of blocks
        upperX = WIDTH - blockWidth - 24;
        upperY = upperY + blockHeight;
        for (int i = 0; i < 11; i++) {
            Block block = new Block(new Point(upperX, upperY), blockWidth,
                    blockHeight, Color.red, hitListeners, super.getScoreTrack());
            upperX -= 47;
            blocks.add(block);

        }
        //creating the 3rd line of blocks
        upperX = WIDTH - blockWidth - 24;
        upperY = upperY + blockHeight;
        for (int i = 0; i < 10; i++) {
            Block block = new Block(new Point(upperX, upperY), blockWidth,
                    blockHeight, Color.CYAN, hitListeners, super.getScoreTrack());
            upperX -= 47;
            blocks.add(block);
        }
        //creating the 4th line of blocks
        upperX = WIDTH - blockWidth - 24;
        upperY = upperY + blockHeight;
        for (int i = 0; i < 9; i++) {
            Block block = new Block(new Point(upperX, upperY), blockWidth,
                    blockHeight, Color.yellow, hitListeners, super.getScoreTrack());
            upperX -= 47;
            blocks.add(block);
        }
        //creating the 5th line of blocks
        upperX = WIDTH - blockWidth - 24;
        upperY = upperY + blockHeight;
        for (int i = 0; i < 8; i++) {
            Block block = new Block(new Point(upperX, upperY), blockWidth,
                    blockHeight, Color.pink, hitListeners, super.getScoreTrack());
            upperX -= 47;
            blocks.add(block);
        }
        //creating the 6th line of blocks
        upperX = WIDTH - blockWidth - 24;
        upperY = upperY + blockHeight;
        for (int i = 0; i < 7; i++) {
            Block block = new Block(new Point(upperX, upperY), blockWidth,
                    blockHeight, Color.green, hitListeners, super.getScoreTrack());
            upperX -= 47;
            blocks.add(block);
        }
        return blocks;
    }
}