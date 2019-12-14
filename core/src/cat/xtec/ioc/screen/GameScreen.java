package cat.xtec.ioc.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import cat.xtec.ioc.helper.AssetManager;
import cat.xtec.ioc.helper.BackgroundScrollHandler;
import cat.xtec.ioc.helper.PillScrollHandler;
import cat.xtec.ioc.helper.InputHandler;
import cat.xtec.ioc.helper.spritemap.FecundationMap;
import cat.xtec.ioc.helper.spritemap.SpermatozoonMap;
import cat.xtec.ioc.object.Ovule;
import cat.xtec.ioc.helper.OvuleScrollHandler;
import cat.xtec.ioc.object.PauseButton;
import cat.xtec.ioc.object.Spermatozoon;
import cat.xtec.ioc.tool.Settings;

public class GameScreen implements Screen {

    public enum GameState { // TODO Exercici 4 Part 0 - Posa el joc en l'estat de PAUSED
        READY, RUNNING, GAME_OVER, PAUSED, RESUMED
    }

    public final static String PREF_LEVEL = "player-level";
    public final static String PREF_HIGHSCORE = "highscore";

    private Preferences preferences = Gdx.app.getPreferences("Score");

    private GameState currentState;

    /* Stage objects */
    private Stage stage;
    private Spermatozoon spermatozoon;
    private BackgroundScrollHandler backgroundScrollHandler;
    private OvuleScrollHandler ovuleScrollHandler;
    private PillScrollHandler pillScrollHandler;
    private PauseButton pauseButton;

    /* Objects in charge of drawing the elements on the screen */
    private ShapeRenderer shapeRenderer;
    private Batch batch;

    private float fecundationTime = 0; // Controls the fecundation effect animation

    private GlyphLayout textLayout; // For writing the text on the screen

    private Label.LabelStyle textStyle;

    /* Objects for the score and the score printing handling */
    private int highscore;
    private String maxPlayerLevel;
    private String playerLevel;
    private int score;
    private Label scoreLabel;
    private Label scoreTextLabel;
    private Container scoreLabelContainer;
    private Container scoreLabelTextContainer;

    public GameScreen(Batch prevBatch, Viewport prevViewport) {
        // TODO Exercici 5.3 - Persistència de la puntuació i el nivell
        if (!preferences.get().isEmpty()) {
            highscore = preferences.getInteger(PREF_HIGHSCORE);
            maxPlayerLevel = preferences.getString(PREF_LEVEL);
        } else { // First time play
            highscore = 0;
            maxPlayerLevel = "N00b";
            preferences.putInteger(PREF_HIGHSCORE, highscore);
            preferences.putString(PREF_LEVEL, maxPlayerLevel);
            preferences.flush();
        }

        // TODO Exercici 4.2 Part I - Efecte gràfic de la puntuació quan puja
        textStyle = new Label.LabelStyle(AssetManager.font, null);

        scoreLabel = new Label(String.valueOf(score), textStyle);

        scoreLabelContainer = new Container<>(scoreLabel);
        scoreLabelContainer.setTransform(true);
        scoreLabelContainer.left();
        scoreLabelContainer.setPosition(40, 6);

        AssetManager.music.setVolume(Settings.NORMAL_VOLUME);
        AssetManager.music.play();

        shapeRenderer = new ShapeRenderer();

        stage = new Stage(prevViewport, prevBatch);

        batch = stage.getBatch();

        backgroundScrollHandler = new BackgroundScrollHandler();
        ovuleScrollHandler = new OvuleScrollHandler();
        pillScrollHandler = new PillScrollHandler(this);
        spermatozoon = new Spermatozoon(Settings.SPERMATOZOON_START_X,
                Settings.SPERMATOZOON_START_Y,
                SpermatozoonMap.WIDTH,
                SpermatozoonMap.HEIGHT);

        pauseButton = new PauseButton(
                Settings.GAME_WIDTH - PauseButton.BUTTON_WIDTH,
                0,
                PauseButton.BUTTON_WIDTH,
                PauseButton.BUTTON_HEIGHT
        );

        scoreTextLabel = new Label("Score: ", textStyle);

        scoreLabelTextContainer = new Container<>(scoreTextLabel);
        scoreLabelTextContainer.center();
        scoreLabelTextContainer.setPosition(20, 6);

        stage.addActor(backgroundScrollHandler);
        stage.addActor(ovuleScrollHandler);
        stage.addActor(pillScrollHandler);
        stage.addActor(scoreLabelContainer);
        stage.addActor(scoreLabelTextContainer);
        stage.addActor(spermatozoon);

        spermatozoon.setName(Spermatozoon.NAME);
        pauseButton.setName(PauseButton.NAME);

        textLayout = new GlyphLayout();

        Gdx.input.setInputProcessor(new InputHandler(this));

        initiate();
    }

    /**
     * Draws all the internal elements. For debugging purposes.
     */
    private void drawElements() {

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix()); // Gathers the batch properties from the stage

        /* Fills the background in black to avoid flickering */
        //Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // Starts the shape renderer
        shapeRenderer.setColor(new Color(0, 1, 0, 1)); // Sets green color

        /* Draws the spermatozoon */
        shapeRenderer.rect(
                spermatozoon.getX(),
                spermatozoon.getY(),
                spermatozoon.getWidth(),
                spermatozoon.getHeight()
        );

        /* Gathers all the ovules */
        ArrayList<Ovule> ovules = ovuleScrollHandler.getOvules();
        Ovule ovule;

        for (int i = 0; i < ovules.size(); i++) {
            ovule = ovules.get(i);

            switch (i) {
                case 0:
                    shapeRenderer.setColor(1, 0, 0, 1);
                    break;
                case 1:
                    shapeRenderer.setColor(0, 0, 1, 1);
                    break;
                case 2:
                    shapeRenderer.setColor(1, 1, 0, 1);
                    break;
                default:
                    shapeRenderer.setColor(1, 1, 1, 1);
                    break;
            }

            shapeRenderer.circle(
                    ovule.getX() + ovule.getWidth() / 2,
                    ovule.getY() + ovule.getWidth() / 2,
                    ovule.getWidth() / 2
            );
        }
        shapeRenderer.end();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        stage.draw();

        switch (currentState) {
            case GAME_OVER:
                updateGameOver(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            case READY:
                updateReady();
                break;
            // TODO Exercici 4 Part II - Posa el joc en l'estat de PAUSED
            case PAUSED:
                updatePaused();
                break;
            case RESUMED:
                updateResumed();
                break;
        }

//        drawElements(); // For debugging purposes
    }

    /**
     * Sets the game elements accordingly with the RESUMED state.
     * Called when the game enters into the RESUMED state.
     */
    private void updateResumed() { // TODO Exercici 4 Part IV - Posa el joc en l'estat de PAUSED
        scoreTextLabel.setVisible(true);
        scoreLabel.setVisible(true);
        AssetManager.music.setVolume(Settings.NORMAL_VOLUME);
        stage.addActor(pauseButton);
        currentState = GameState.RUNNING;
    }

    /**
     * Sets the game elements accordingly with the PAUSED state.
     * Called when the game enters into the PAUSED state.
     */
    private void updatePaused() { // TODO Exercici 4 Part III - Posa el joc en l'estat de PAUSED
        pauseButton.remove();
        scoreTextLabel.setVisible(false);
        scoreLabel.setVisible(false);
        AssetManager.music.setVolume(Settings.PAUSE_VOLUME);
    }

    /**
     * Sets the game elements accordingly with the READY state.
     * Called when the game enters into the READY state.
     */
    private void updateReady() {
        batch.begin();
        AssetManager.font.draw(
                batch,
                textLayout,
                (Settings.GAME_WIDTH / 2f) - textLayout.width / 2,
                (Settings.GAME_HEIGHT / 2f) - textLayout.height / 2
        );
        batch.end();
    }

    /**
     * Sets the game elements accordingly with the RUNNING state.
     * Called when the game enters into the RUNNING state.
     *
     * @param delta Time in seconds since the last frame.
     */
    private void updateRunning(float delta) {
        stage.act(delta);

        if (ovuleScrollHandler.collides(spermatozoon)) {
            // TODO Exercici 5.1 - Missatges personalitzats en funció de la puntuació
            String looseMessage = "\n\n";

            if (score < 100) {
                playerLevel = "N00b";
                looseMessage += "You're a n00b!";
            } else if (score < 150) {
                playerLevel = "Person";
                looseMessage += "Well done!";
            } else {
                playerLevel = "Pro";
                looseMessage += "Oh yeah!! You're a pro!";
            }

            AssetManager.fecundationSound.play();
            stage.getRoot().findActor(Spermatozoon.NAME).remove();
            textLayout.setText(AssetManager.font, "Game Over :'(" + looseMessage);
            currentState = GameState.GAME_OVER;
        }

        if (pillScrollHandler.collides(spermatozoon)) {
            AssetManager.coinSound.play();

            pillScrollHandler.reset();

            // TODO Exercici 4.2 Part II - Efecte gràfic i acústic de la puntuació quan puja
            scoreLabelContainer.addAction(
                    Actions.sequence(
                            Actions.scaleTo(1.5f, 1.5f, 0.2f),
                            Actions.scaleTo(1f, 1f, 0.2f)
                    )
            );
        }
    }

    /**
     * Sets the game elements accordingly with the GAME_OVER state.
     * Called when the game enters into the GAME_OVER state.
     *
     * @param delta Time in seconds since the last frame.
     */
    private void updateGameOver(float delta) {
        stage.act(delta);

        pauseButton.remove();

        batch.begin();

        AssetManager.font.draw(
                batch,
                textLayout,
                (Settings.GAME_WIDTH - textLayout.width) / 2,
                (Settings.GAME_HEIGHT - textLayout.height) / 2
        );

        batch.draw(
                (TextureRegion) AssetManager.fecundationAnim.getKeyFrame(fecundationTime,
                        false),
                (spermatozoon.getX() + spermatozoon.getWidth() / 2) - 32,
                spermatozoon.getY() + spermatozoon.getHeight() / 2 - 32,
                FecundationMap.WIDTH,
                FecundationMap.HEIGHT
        );
        batch.end();

        fecundationTime += delta;

        if (score > highscore) {
            highscore = score;
            maxPlayerLevel = playerLevel;
            // TODO Exercici 5.3 - Persistència de la puntuació i el nivell
            preferences.putInteger(PREF_HIGHSCORE, highscore);
            preferences.putString(PREF_LEVEL, maxPlayerLevel);
            preferences.flush();
        }
    }

    // TODO Exercici 5.2 - High scores i nivell maxims del jugador

    /**
     * Shows on the screen the just-before-RUNNING state elements.
     */
    private void initiate() {
        score = 0;
        fecundationTime = 0.0f;

        String readyMessage = "Highscore: " + highscore + "\nPlayer level: " + maxPlayerLevel
                + "\n\nAre you\nready?";

        textLayout.setText(AssetManager.font, readyMessage);
        currentState = GameState.READY;
        stage.addActor(spermatozoon);
        scoreLabel.setText(String.valueOf(score));
    }

    /**
     * Resets all the actors of the stage.
     */
    public void reset() {
        spermatozoon.reset();
        ovuleScrollHandler.reset();
        pillScrollHandler.reset();

        initiate();
    }


    @Override
    public void resize(int width, int height) { // Unused

    }

    @Override
    public void pause() { // Unused

    }

    @Override
    public void resume() { // Unused

    }

    @Override
    public void hide() { // Unused

    }

    @Override
    public void dispose() { // Unused

    }

    public PauseButton getPauseButton() {
        return pauseButton;
    }

    public Spermatozoon getSpermatozoon() {
        return spermatozoon;
    }

    public Stage getStage() {
        return stage;
    }

    public OvuleScrollHandler getOvuleScrollHandler() {
        return ovuleScrollHandler;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Container getScoreLabelTextContainer() {
        return scoreLabelTextContainer;
    }
}
