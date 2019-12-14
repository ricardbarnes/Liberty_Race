package cat.xtec.ioc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cat.xtec.ioc.SpaceRace;
import cat.xtec.ioc.helper.AssetManager;
import cat.xtec.ioc.tool.Methods;
import cat.xtec.ioc.tool.Settings;

public class SplashScreen implements Screen {

    private Stage stage;
    private SpaceRace game;

    private Label.LabelStyle textStyle;
    private Label textLbl;

    public SplashScreen(SpaceRace game) {

        this.game = game;

        OrthographicCamera camera = new OrthographicCamera(
                Settings.GAME_WIDTH, Settings.GAME_HEIGHT
        );
        camera.setToOrtho(true); // Y-Down

        StretchViewport viewport = new StretchViewport(
                Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera
        );

        stage = new Stage(viewport);
        stage.addActor(new Image(AssetManager.background));

        textStyle = new Label.LabelStyle(AssetManager.font, null);
        textLbl = new Label("LibertyRace", textStyle);

        /* Action container */
        Container container = new Container<>(textLbl);
        container.setTransform(true);
        container.center();
        container.setPosition(Settings.GAME_WIDTH / 2, Settings.GAME_HEIGHT / 2);

        // TODO Exercici 2.1 - Instruccions per a aconseguir les animacions demanades per a la splash screen.
        container.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(
                Actions.alpha(0f),
                Actions.parallel(
                        Actions.alpha(1f, 1),
                        Actions.scaleTo(1.5f, 1.5f, 1)
                ),
                Actions.parallel(
                        Actions.alpha(0f, 1),
                        Actions.scaleTo(1, 1, 1)))
                )
        );

        // TODO Exercici 2.2 - Instruccions per a fer que el recorregut horitzontal tingui punts inicial i final aleatoris.
        Image spermatozoon = new Image(AssetManager.spermatozoonStraight);

        float initialY = Methods.randomFloat(
                0 + spermatozoon.getHeight(),
                Settings.GAME_HEIGHT - spermatozoon.getHeight()
        );
        float finalY = Methods.randomFloat(
                0 + spermatozoon.getHeight(),
                Settings.GAME_HEIGHT - spermatozoon.getHeight()
        );

        if (initialY < finalY) { // Goes upward
            spermatozoon.setDrawable(new SpriteDrawable(new Sprite(AssetManager.spermatozoonDown)));
        } else if (initialY > finalY) { // Goes downward
            spermatozoon.setDrawable(new SpriteDrawable(new Sprite(AssetManager.spermatozoonUp)));
        }

        spermatozoon.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(
                Actions.moveTo(0 - spermatozoon.getWidth(), initialY),
                Actions.moveTo(Settings.GAME_WIDTH, finalY, 5)
        )));

        stage.addActor(spermatozoon);
        stage.addActor(container);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.draw();
        stage.act(delta);

        if (Gdx.input.isTouched()) { // "On click"
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
