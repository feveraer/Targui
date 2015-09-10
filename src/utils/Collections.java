package utils;

import java.util.Random;

/**
 * Custom implementation of several sort and shuffle algorithms for collections.
 *
 * @author Frederic Everaert
 * @author Gilles Baert
 * @author Jonas De Bruycker
 * @author Sander De Quick
 */
public class Collections {

    /**
     * Shuffles an array randomly in groups of n.
     *
     * @param array
     * @param n Must be an integer greater than 0 and smaller and dividable by
     * array.length.
     */
    public static void shuffle(Object[] array, int n) {
        if (array.length % n != 0) {
            throw new IllegalArgumentException("Array of length " + array.length + " can't be shuffled in groups of " + n);
        }
        Random rnd = new Random();
        for (int i = array.length - n; i > n - 1; i -= n) {
            int index = rnd.nextInt((i + n) / n) * n;
            for (int j = 0; j < n; j++) {
                Object a = array[index + j];
                array[index + j] = array[i + j];
                array[i + j] = a;
            }
        }
    }

}
