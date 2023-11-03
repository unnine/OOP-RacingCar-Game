package util;

import java.util.Random;

public class RandomNumber {

    private static final Random random = new Random();

    public static int betweenRange(int min, int max) {
        return random.nextInt(min, max + 1);
    }

}