package cat.xtec.ioc.helper.spritemap;

public class SpermatozoonMap {

    public static final int WIDTH = 33;
    public static final int HEIGHT = 10;
    public static final int SPRITES = 3;

    public static final int[] STRAIGHT_POSITION_COORDINATES = { // x, y
            BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH, 0
    };

    public static final int[] UP_POSITION_COORDINATES = { // x, y
            BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 2, 0
    };

    public static final int[] DOWN_POSITION_COORDINATES = { // x, y
            BluePillMap.WIDTH + YellowPillMap.WIDTH, 0
    };

}
