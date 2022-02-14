package com.example.jobdemo.util;

import java.util.Random;

/**
 * @Author LYX
 * @Date 2022/1/27 15:47
 */
public class RandomUtils {

    private final Random random;

    private RandomUtils() {
        random = new Random();
    }

    private static class SingleRandomUtils {
        private static final RandomUtils RANDOMUTILS = new RandomUtils();
    }

    public static RandomUtils getInstance() {
        return SingleRandomUtils.RANDOMUTILS;
    }

    private int getRandomInt() {
        return random.nextInt();
    }
}
