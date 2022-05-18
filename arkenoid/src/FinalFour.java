import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class: FinalFour.
 * <p>
 * a type of level.
 * has some static values that are shared with all FinalFours.
 */
public class FinalFour extends Level {
    //the following fields are static because i don't mind all the direct hit levels will share them.
    //it also solves a constructor problem.
    private static int numOfBalls = 3;
    private static int paddleSpeed = 10;
    private static int paddleWidth = 100;
    private static Point blockLocation = new Point(24, 200);
    private static int blockWidth = 47;
    private static int blockHeight = 20;


    /**
     * constructor.
     * creates a the FinalFour level.
     *
     * @param scoreTrack the scoreTrack field.
     */
    public FinalFour(ScoreTrackingListener scoreTrack) {
        super(numOfBalls, paddleSpeed, paddleWidth, "Final Four", scoreTrack);
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Ball ball = new Ball(new Point(370 + i * 50, 560 - i * 10), 6, Color.WHITE, hitListeners);
            balls.add(ball);
        }
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(40 + i * 20, Level.BALL_SPEED));
        }
        return ballsVelocity;
    }


    @Override
    protected Sprite initBackground() {
        return new Block(new Point(0, 0),
                Level.WIDTH, Level.HEIGHT, Color.CYAN);
    }

    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<HitListener>();
        int upperX = WIDTH - blockWidth - 24;
        int upperY = 100;
        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 16; i++) {
                Block block = new Block(new Point(upperX, upperY), blockWidth, blockHeight,
                        this.colorChanger(j), hitListeners, super.getScoreTrack());
                upperX -= 47;
                blocks.add(block);
            }
            upperX = WIDTH - blockWidth - 24;
            upperY += blockHeight;
        }
        return blocks;
    }
    /**
     * method: colorChanger.
     * changes the color by given number
     *
     * @param num the given number.
     * @return a color.
     */
    private Color colorChanger(int num) {
        switch (num) {
            case 0:
                return Color.GRAY;
            case 1:
                return Color.RED;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 5:
                return Color.PINK;
            case 6:
                return Color.CYAN;
            default:
                return Color.WHITE;
        }
    }
}
