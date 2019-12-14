package cat.xtec.ioc.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cat.xtec.ioc.helper.spritemap.BackgroundMap;
import cat.xtec.ioc.helper.spritemap.BluePillMap;
import cat.xtec.ioc.helper.spritemap.FecundationMap;
import cat.xtec.ioc.helper.spritemap.OvuleMap;
import cat.xtec.ioc.helper.spritemap.PauseButtonMap;
import cat.xtec.ioc.helper.spritemap.SpermatozoonMap;
import cat.xtec.ioc.helper.spritemap.YellowPillMap;
import cat.xtec.ioc.object.PauseButton;

// TODO Exercici 1 - Classe que gestiona els assets del joc.
public class AssetManager {

    public static Texture sheet;

    public static TextureRegion spermatozoonStraight, spermatozoonDown, spermatozoonUp, background;

    public static TextureRegion ovule;

    public static TextureRegion yellowCoin;

    public static TextureRegion blueCoin;

    public static TextureRegion[] fecundation;
    public static Animation fecundationAnim;

    public static TextureRegion pauseButton;

    public static Sound fecundationSound;
    public static Sound coinSound;
    public static Music music;

    public static BitmapFont font;

    /**
     * Loads all the required assets for the game.
     */
    public static void load() {
        sheet = new Texture(Gdx.files.internal("sheet.png"));
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        spermatozoonStraight = new TextureRegion(
                sheet,
                SpermatozoonMap.STRAIGHT_POSITION_COORDINATES[0],
                SpermatozoonMap.STRAIGHT_POSITION_COORDINATES[1],
                SpermatozoonMap.WIDTH,
                SpermatozoonMap.HEIGHT
        );
        spermatozoonStraight.flip(false, true);

        spermatozoonUp = new TextureRegion(
                sheet,
                SpermatozoonMap.UP_POSITION_COORDINATES[0],
                SpermatozoonMap.UP_POSITION_COORDINATES[1],
                SpermatozoonMap.WIDTH,
                SpermatozoonMap.HEIGHT
        );
        spermatozoonUp.flip(false, true);

        spermatozoonDown = new TextureRegion(
                sheet,
                SpermatozoonMap.DOWN_POSITION_COORDINATES[0],
                SpermatozoonMap.DOWN_POSITION_COORDINATES[1],
                SpermatozoonMap.WIDTH,
                SpermatozoonMap.HEIGHT
        );
        spermatozoonDown.flip(false, true);

        ovule = new TextureRegion(sheet,
                OvuleMap.COORDINATES[0],
                OvuleMap.COORDINATES[1],
                OvuleMap.WIDTH,
                OvuleMap.HEIGHT);

        fecundation = new TextureRegion[FecundationMap.SPRITES];
        for (int i = 0; i < fecundation.length; i++) {
            fecundation[i] = new TextureRegion(
                    sheet,
                    FecundationMap.COORDINATES[i][0],
                    FecundationMap.COORDINATES[i][1],
                    FecundationMap.WIDTH,
                    FecundationMap.HEIGHT
            );
        }
        fecundationAnim = new Animation<>(0.04f, fecundation);

        yellowCoin = new TextureRegion(
                sheet,
                YellowPillMap.COORDINATES[0],
                YellowPillMap.COORDINATES[1],
                YellowPillMap.WIDTH,
                YellowPillMap.HEIGHT
        );

        blueCoin = new TextureRegion(
                sheet,
                BluePillMap.COORDINATES[0],
                BluePillMap.COORDINATES[1],
                BluePillMap.WIDTH,
                BluePillMap.HEIGHT
        );

        background = new TextureRegion(
                sheet,
                BackgroundMap.COORDINATES[0],
                BackgroundMap.COORDINATES[1],
                BackgroundMap.WIDTH,
                BackgroundMap.HEIGHT
        );
        background.flip(true, true);

        /* Buttons */
        pauseButton = new TextureRegion(
                sheet,
                PauseButtonMap.COORDINATES[0],
                PauseButtonMap.COORDINATES[1],
                PauseButtonMap.WIDTH,
                PauseButtonMap.HEIGHT
        );

        /* Sounds */
        fecundationSound = Gdx.audio.newSound(Gdx.files.internal("sounds/conception.wav"));

        coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pill-take.wav"));

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Afterburner.ogg"));
        music.setLooping(true);

        /* Text */
        FileHandle fontFile = Gdx.files.internal("fonts/monster.fnt");
        font = new BitmapFont(fontFile, true);
        font.getData().setScale(0.4f);
    }

    /**
     * Releases the game resources.
     */
    public static void dispose() {
        sheet.dispose();
        fecundationSound.dispose();
        music.dispose();
    }
}
