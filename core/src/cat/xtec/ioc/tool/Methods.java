package cat.xtec.ioc.tool;

import java.util.Random;

public class Methods {

    /**
     * Creates a random float number between a specified maximum and minimum.
     *
     * @param min the minimum for the random number.
     * @param max the maximum for the random number.
     * @return a random float number into the specified range.
     */
    public static float randomFloat(float min, float max) {
        Random r = new Random();
        return r.nextFloat() * (max - min) + min;
    }

    /**
     * Creates a random integer number between a specified maximum and minimum.
     *
     * @param min the minimum for the random number.
     * @param max the maximum for the random number.
     * @return a random integer number into the specified range.
     */
    public static int randomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt() * (max - min) + min;
    }
}