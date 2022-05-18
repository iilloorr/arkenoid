/* ID: 208916189*/

import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

/**
 * class: Ass6Game.
 * <p>
 * the main function for our game.
 */
public class Ass6Game {
    /**
     * method: main
     * <p>
     * runs the game. levels are determined by args.
     *
     * @param args gets the level to run.
     *             by the numbers 1 to 4. ignores any other strings.
     *             if no parameters are given(or all the parameters arent numbers
     *             between 1 and 4) running the 4 stages.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 60, new Sleeper());
        GameFlow flow = new GameFlow(new ScoreTrackingListener(new Counter(0)), runner, gui);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].length() == 1) {
                switch (args[i].charAt(0)) {
                    case '1':
                        levels.add(new DirectHit(flow.getScoreTrack()));
                        break;
                    case '2':
                        levels.add(new WideEasy(flow.getScoreTrack()));
                        break;
                    case '3':
                        levels.add(new GreenThree(flow.getScoreTrack()));
                        break;
                    case '4':
                        levels.add(new FinalFour(flow.getScoreTrack()));
                        break;
                    default:
                        continue;
                }
            }
        }
        if (levels.size() == 0) {
            Level directHit = new DirectHit(flow.getScoreTrack());
            levels.add(directHit);
            Level wideEasy = new WideEasy(flow.getScoreTrack());
            levels.add(wideEasy);
            Level greenThree = new GreenThree(flow.getScoreTrack());
            levels.add(greenThree);
            Level finalFour = new FinalFour(flow.getScoreTrack());
            levels.add(finalFour);
        }
        flow.runLevels(levels);

    }
}

