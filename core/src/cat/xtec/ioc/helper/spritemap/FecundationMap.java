package cat.xtec.ioc.helper.spritemap;

public class FecundationMap {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int SPRITES = 15;

    public static final int[][] COORDINATES = { // x, y
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 7,
                    0}, // #1
            {WIDTH,
                    HEIGHT}, // #2
            {0, HEIGHT}, // #3
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 10,
                    0}, // #4
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH,
                    0}, // #5
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 4,
                    0}, // #6
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH,
                    0}, // #7
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 3,
                    0}, // #8
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 2,
                    0}, // #9
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 8,
                    0}, // #10
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 6,
                    0}, // #11
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 5,
                    0}, // #12
            {BluePillMap.WIDTH + YellowPillMap.WIDTH + SpermatozoonMap.WIDTH * 3 + OvuleMap.WIDTH + WIDTH * 9,
                    0}, // #13
            {WIDTH * 2,
                    HEIGHT}, // #14
            {WIDTH * 3,
                    HEIGHT} // #15
    };

}
