package cat.xtec.ioc.helper;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import cat.xtec.ioc.object.PauseButton;
import cat.xtec.ioc.object.Spermatozoon;
import cat.xtec.ioc.screen.GameScreen;

public class InputHandler implements InputProcessor {

    int previousY = 0; // Drag management integer

    /* Needed objects */
    private Spermatozoon spermatozoon;
    private GameScreen screen;
    private Vector2 stageCoordinates;

    private Stage stage;

    public InputHandler(GameScreen screen) {
        this.screen = screen;
        spermatozoon = screen.getSpermatozoon();
        stage = screen.getStage();
    }

    @Override
    public boolean keyDown(int keycode) { // Unused
        return false;
    }

    @Override
    public boolean keyUp(int keycode) { // Unused
        return false;
    }

    @Override
    public boolean keyTyped(char character) { // Unused
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (screen.getCurrentState()) {
            case READY:
                screen.getStage().addActor(screen.getPauseButton());
                screen.setCurrentState(GameScreen.GameState.RUNNING);
                break;
            case RUNNING:
                previousY = screenY;

                stageCoordinates = stage.screenToStageCoordinates(new Vector2(screenX, screenY));

                Actor actorHit = stage.hit(stageCoordinates.x, stageCoordinates.y, true);
                if (actorHit != null) {
                    if (actorHit.getName() == PauseButton.NAME) {
                        // TODO Exercici 4 Part I - Posa el joc en l'estat de PAUSED
                        screen.setCurrentState(GameScreen.GameState.PAUSED);
                    }
                }
                break;
            case PAUSED:
                screen.setCurrentState(GameScreen.GameState.RESUMED);
                break;
            case GAME_OVER:
                screen.reset();
                break;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        spermatozoon.goStraight();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Math.abs(previousY - screenY) > 2) // Threshold
            if (previousY < screenY) {
                spermatozoon.goDown();
            } else {
                spermatozoon.goUp();
            }
        previousY = screenY;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
