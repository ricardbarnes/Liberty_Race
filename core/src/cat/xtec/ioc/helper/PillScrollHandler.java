package cat.xtec.ioc.helper;

import com.badlogic.gdx.scenes.scene2d.Group;

import cat.xtec.ioc.helper.spritemap.BluePillMap;
import cat.xtec.ioc.helper.spritemap.YellowPillMap;
import cat.xtec.ioc.object.BluePill;
import cat.xtec.ioc.object.Spermatozoon;
import cat.xtec.ioc.object.YellowPill;
import cat.xtec.ioc.screen.GameScreen;
import cat.xtec.ioc.tool.Methods;
import cat.xtec.ioc.tool.Settings;

// TODO Exercici 3.1 - Classe per a la gestió de les pastilles
public class PillScrollHandler extends Group {

    private YellowPill yellowPill;
    private BluePill bluePill;

    private GameScreen gameScreen;
    private boolean isYellowPill;

    public PillScrollHandler(GameScreen gameScreen) {

        this.gameScreen = gameScreen;

        float chance = Methods.randomFloat(0.00f, 100.00f);

        yellowPill = new YellowPill(
                Settings.GAME_WIDTH,
                Methods.randomInt(
                        YellowPillMap.HEIGHT / 2,
                        Settings.GAME_HEIGHT - YellowPillMap.HEIGHT / 2
                ),
                YellowPillMap.WIDTH / 2,
                YellowPillMap.HEIGHT / 2,
                Settings.YELLOW_PILL_SPEED
        );

        bluePill = new BluePill(
                Settings.GAME_WIDTH,
                Methods.randomInt(
                        BluePillMap.HEIGHT / 2,
                        Settings.GAME_HEIGHT - BluePillMap.HEIGHT / 2
                ),
                BluePillMap.WIDTH / 2,
                BluePillMap.HEIGHT / 2,
                Settings.BLUE_PILL_SPEED
        );

        if (chance < Settings.YELLOW_PILL_CHANCE) {
            addActor(yellowPill);
            isYellowPill = true;
        } else {
            addActor(bluePill);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (yellowPill.isLeftOfScreen()) {
            yellowPill.reset(Settings.GAME_WIDTH);
        } else if (bluePill.isLeftOfScreen()) {
            bluePill.reset(Settings.GAME_WIDTH);
        }
    }

    /**
     * Checks if the spermatozoon collides with any pill.
     *
     * @param spermatozoon The spermatozoon object.
     * @return true if there has been a collision, false otherwise.
     */
    public boolean collides(Spermatozoon spermatozoon) {
        if (yellowPill.collides(spermatozoon) || bluePill.collides(spermatozoon)) {
            return true;
        }
        return false;
    }

    /**
     * Resets the pill position to the beginning of the screen, with new Y random positions.
     * The pill might be yellow or blue depending on the result of the random parameters.
     */
    public void reset() {
        float chance = Methods.randomFloat(0.00f, 100.00f);

        if (isYellowPill) {
            yellowPill.reset(Settings.GAME_WIDTH);
            removeActor(yellowPill);
            isYellowPill = false;
            gameScreen.setScore(gameScreen.getScore() + Settings.YELLOW_PILL_POINTS);
        } else {
            bluePill.reset(Settings.GAME_WIDTH);
            removeActor(bluePill);
            gameScreen.setScore(gameScreen.getScore() + Settings.BLUE_PILL_POINTS);
        }

        gameScreen.getScoreLabel().setText(gameScreen.getScore());

        if (chance < Settings.YELLOW_PILL_CHANCE) {
            addActor(yellowPill);
            yellowPill.reset(Settings.GAME_WIDTH);
            isYellowPill = true;
        } else {
            addActor(bluePill);
            bluePill.reset(Settings.GAME_WIDTH);
        }
    }

}