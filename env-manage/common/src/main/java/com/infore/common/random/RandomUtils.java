package com.infore.common.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static final Random r = new Random();

    public static <T> List<T> getRandomElements(List<T> list, int num) {
        return getRandomElements(list, num, r);
    }

    public static <T> List<T> getRandomElements(List<T> list, int num, Random r) {
        if (list == null || num < 0) {
            throw new IllegalArgumentException("Incorrect args: list " + list + ", num [" + num + "]");
        }

        List<T> results = new ArrayList<>();
        if (list.isEmpty() || num == 0) {
            return results;
        }

        int len = list.size();
        for (int i = 0; i < num; i++) {
            results.add(list.get(r.nextInt(len)));
        }

        return results;
    }

    public static <T> List<T> getRandomDistinctElements(List<T> list, int num) {
        return getRandomDistinctElements(list, num, r);
    }

    public static <T> List<T> getRandomDistinctElements(List<T> list, int num, Random r) {
        if (list == null || num < 0) {
            throw new IllegalArgumentException("Incorrect args: list " + list + ", num [" + num + "].");
        }

        int len = list.size();
        if (num > len) {
            throw new IllegalArgumentException("Incorrect args: num [" + num + "] is greater than list size [" + len + "].");
        }

        List<T> results = new ArrayList<>();
        if (num == 0) {
            return results;
        }

        List<T> tmpList = new ArrayList<>(list);
        Collections.shuffle(tmpList, r);
        for (int i = 0; i < num; i++) {
            results.add(tmpList.get(i));
        }

        return results;
    }

}
