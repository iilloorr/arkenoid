import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class: GameLevel.
 * <p>
 * has a collection of sprites and collidables (in environment field).
 * also has a gui as a field which is the gui that is the screen of the game.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private KeyboardSensor keyboard;
    private LevelInformation info;
    private Counter numOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Counter numOfBlocks;
    private ScoreTrackingListener scoreTrack;

    /**
     * constructor.
     *
     * @param info     the information given of a specific level.
     * @param runner   the animation runner field.
     * @param keyboard the keyboard field.
     * @param gui      the gui field.
     */
    public GameLevel(LevelInformation info, AnimationRunner runner, KeyboardSensor keyboard, GUI gui) {
        this.info = info;
        this.numOfBalls = new Counter(info.getNumOfBalls());
        this.sprites.addSprite(info.getBackground());
        this.paddleSpeed = info.paddleSpeed();
        this.paddleWidth = info.paddleWidth();
        this.levelName = info.levelName();
        this.numOfBlocks = new Counter(info.numberOfBlocksToRemove());
        this.scoreTrack = info.getScoreTrack();
        this.ballsVelocities = info.initialBallVelocities();
        this.keyboard = keyboard;
        this.runner = runner;
        this.gui = gui;

    }

    /**
     * method: addCollidable.
     * adds a collidable to the game environment field
     *
     * @param c the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * method: addSprite.
     * adds a sprite to the sprite collection field
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * method: initialize.
     * initializes the level, defining the paddle (including keyboard sensor), the 2 balls and the blocks
     * (including the borders blocks). also notifies the balls of the game environment.
     * by initializing the level adds ball into the sprite collection, and adds paddle and blocks
     * to both sprite collection and collidable collection in game environment.
     */
    public void initialize() {
        //initializing the scoreIndicator
        Sprite indicator = new ScoreIndicator(this.scoreTrack.getCurrentScore());
        this.addSprite(indicator);
        //adding the block remover listener
        this.blockRemover = new BlockRemover(this, numOfBlocks);
        //adding the ball remover listener
        this.ballRemover = new BallRemover(this, numOfBalls);
//        ArrayList<HitListener> ballListeners = new ArrayList<HitListener>();

        //creating the balls
        List<Ball> balls = this.info.balls();
        for (Ball ball : balls) {
            ball.addHitListeners(ballRemover);
            ball.setVelocity(this.ballsVelocities.get(balls.indexOf(ball)));
            ball.setTrajectory();
            ball.addToGame(this);
        }
        //ballListeners.add(ballRemover);

        //creating the blocks
        List<Block> blocks = this.info.blocks();
        blocks.get(0).addHitListener(blockRemover);
        blocks.get(0).addHitListener(scoreTrack);
        for (Block block : blocks) {
            block.addToGame(this);
        }

        //creating the borders
        Block border = new Block(new Point(0, 20), 780, 40, Color.gray);
        border.addToGame(this);
        border = new Block(new Point(0, 20), 24, 580, Color.gray);
        border.addToGame(this);
        border = new Block(new Point(776, 20), 24, 600, Color.gray);
        border.addToGame(this);
        border = new Block(new Point(24, 600), 776, 20, Color.gray, new ArrayList<HitListener>());
        border.addHitListener(ballRemover);
        border.addToGame(this);
        //add the paddle

        Paddle paddle = new Paddle(new Rectangle(new Point((Level.WIDTH - this.paddleWidth) / 2, 580),
                this.paddleWidth, 20), keyboard, this.paddleSpeed);
        paddle.addToGame(this);
        //notifying the balls of the game environment
        for (Ball ball : balls) {
            ball.setGameEnvironment(environment);
        }
    }

    /**
     * method: run.
     * Run the game -- start the animation loop.
     */
    public void run() {

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run((Animation) this);


    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d = gui.getDrawSurface();
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        gui.show(d);
        this.sprites.notifyAllTimePassed();
        if (this.numOfBlocks.getValue() == 0) {
            this.scoreTrack.getCurrentScore().increase(100);
            this.running = false;
            return;
        }
        if (this.numOfBalls.getValue() == 0) {
            this.running = false;
            return;
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard, this.gui));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * method: removeCollidable.
     * removes a collidable from the environment field.
     *
     * @param c the collidable to be removed
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * method: removeSprite.
     * removes a sprite from the sprites field.
     *
     * @param s the sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * method: getNumOfBalls.
     *
     * @return the number of balls left
     */
    public Counter getNumOfBalls() {
        return numOfBalls;
    }
    /**
     * method: getNumOfBlocks.
     *
     * @return the number of blocks left
     */
    public Counter getNumOfBlocks() {
        return numOfBlocks;
    }
}