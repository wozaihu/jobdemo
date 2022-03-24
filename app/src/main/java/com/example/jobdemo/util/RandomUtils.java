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

    /**
     * @return 返回一个随机数
     */
    public int getRandomInt() {
        return random.nextInt();
    }

    /**
     * @return 返回随机年龄
     */
    public int getRandomAge() {
        return random.nextInt(99) + 1;
    }

    /**
     * @param size 几位数的ID
     * @return 返回随机ID
     */
    public String getRandomInt(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
