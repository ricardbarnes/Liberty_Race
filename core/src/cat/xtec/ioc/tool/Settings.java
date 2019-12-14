package cat.xtec.ioc.tool;

import cat.xtec.ioc.helper.spritemap.SpermatozoonMap;

public class Settings {

    /* Music volume settings */
    public static final float NORMAL_VOLUME = 0.5f;
    public static final float PAUSE_VOLUME = 0.025f;

    /* Game screen size in pixels */
    public static final int GAME_WIDTH = 240;
    public static final int GAME_HEIGHT = 135;

    /* Spermatozoon properties */
    public static final float SPERMATOZOON_SPEED = 50;
    public static final float SPERMATOZOON_START_X = 20;
    public static final float SPERMATOZOON_START_Y = GAME_HEIGHT / 2 - SpermatozoonMap.HEIGHT / 2;

    /* Scaling values for the ovules */
    public static final float MAX_OVULE = 1.5f;
    public static final float MIN_OVULE = 0.5f;

    /* Ovule scrollable configuration */
    public static final int OVULE_SPEED = -150;
    public static final int OVULE_GAP = 75;

    /* Background scrollable configuration */
    public static final int BG_SPEED = -100;

    // TODO Exercici 2: Propietats per a la moneda
    /* Yellow pill properties */
    public static final int YELLOW_PILL_SPEED = -175;
    public static final int YELLOW_PILL_POINTS = 10;
    public static final float YELLOW_PILL_CHANCE = 90.00f;

    /* Blue pill properties */
    public static final int BLUE_PILL_SPEED = -250;
    public static final int BLUE_PILL_POINTS = 50;

}
