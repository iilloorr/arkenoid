import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class: WideEasy.
 * <p>
 * a type of level.
 * has some static values that are shared with all WideEasy.
 */
public class WideEasy extends Level {
    //the following fields are static because i don't mind all the direct hit levels will share them.
    //it also solves a constructor problem.
    private static int numOfBalls = 10;
    private static int paddleSpeed = 10;
    private static int paddleWidth = 400;
    private static Point blockLocation = new Point(24, 200);
    private static int blockWidth = 47;
    private static int blockHeight = 30;


    /**
     * constructor.
     * creates a the WideEasy level.
     *
     * @param scoreTrack the scoreTrack field.
     */
    public WideEasy(ScoreTrackingListener scoreTrack) {
        super(numOfBalls, paddleSpeed, paddleWidth, "Wide Easy", scoreTrack);
    }

    @Override
    public List<Ball> balls() {
        List<Ball> balls = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ball ball = new Ball(new Point(180 + i * 50, 560 - i * 10), 6,
                    Color.WHITE, hitListeners);
            balls.add(ball);
        }
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> ballsVelocity = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ballsVelocity.add(Velocity.fromAngleAndSpeed(-80 + i * 20, Level.BALL_SPEED));
        }
        return ballsVelocity;
    }


    @Override
    protected Sprite initBackground() {
        return new Block(new Point(0, 0),
                Level.WIDTH, Level.HEIGHT, Color.white);
    }

    @Override
    public ArrayList<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<HitListener> hitListeners = new ArrayList<HitListener>();
        for (int i = 0; i < 16; i++) {
            blocks.add(new Block(new Point(this.blockLocation.getX() + (i * blockWidth), this.blockLocation.getY()),
                    this.blockWidth, this.blockHeight, this.colorChanger(i), hitListeners, super.getScoreTrack()));

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
            case 1:
                return Color.RED;
            case 2:
            case 3:
                return Color.ORANGE;
            case 4:
            case 5:
                return Color.YELLOW;
            case 6:
            case 7:
            case 8:
                return Color.GREEN;
            case 9:
            case 10:
            case 11:
                return Color.CYAN;
            case 12:
            case 13:
                return Color.BLUE;
            case 14:
            case 15:
                return Color.PINK;
            default:
                return Color.WHITE;
        }
    }
}