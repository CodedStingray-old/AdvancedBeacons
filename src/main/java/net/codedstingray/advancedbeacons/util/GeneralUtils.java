package net.codedstingray.advancedbeacons.util;

public class GeneralUtils {

    public static <T> boolean smaller(Comparable<T> comparer, T compared) {
         return comparer.compareTo(compared) < 0;
    }

    public static <T> boolean equal(Comparable<T> comparer, T compared) {
        return comparer.compareTo(compared) == 0;
    }

    public static <T> boolean greater(Comparable<T> comparer, T compared) {
        return comparer.compareTo(compared) > 0;
    }
}
