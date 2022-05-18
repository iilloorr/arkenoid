import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * class: GameFlow.
 * <p>
 * a class that is responsible on the flow of the game,
 * the transition between levels and keeping the score.
 */
public class GameFlow {
    private ScoreTrackingListener scoreTrack;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;

    /**
     * constructor.
     *
     * @param ar         the animation runner field
     * @param gui        the gui field.
     * @param scoreTrack the score track field
     */
    public GameFlow(ScoreTrackingListener scoreTrack, AnimationRunner ar, GUI gui) {
        this.scoreTrack = scoreTrack;
        this.ar = ar;
        this.gui = gui;
        this.ks = this.gui.getKeyboardSensor();
    }

    /**
     * Method: getScoreTrack.
     * returns scoreTrack
     *
     * @return the scoreTrack field.
     */
    public ScoreTrackingListener getScoreTrack() {
        return scoreTrack;
    }

    /**
     * Method: runLevels.
     * running all the levels given
     *
     * @param levels the levels that are running.
     */
    public void runLevels(List<LevelInformation> levels) {
        if (levels.size() == 0) {
            gui.close();
        }
        int i = 0;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, this.gui);

            level.initialize();

            while (level.getNumOfBlocks().getValue() > 0 && level.getNumOfBalls().getValue() > 0) {
                level.run();
                i++;
            }

            if (level.getNumOfBalls().getValue() == 0) {
                ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new EndScreen(level,
                        this.gui, this.scoreTrack.getCurrentScore())));
                break;
            }
            if (i == levels.size()) {
                ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY, new EndScreen(level,
                        this.gui, this.scoreTrack.getCurrentScore())));
            }
        }
        if (this.ks.isPressed(KeyboardSensor.SPACE_KEY)) {
            ar.getGui().close();
        }
    }
}