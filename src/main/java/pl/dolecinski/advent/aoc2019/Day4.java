package pl.dolecinski.advent.aoc2019;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 {


    public static void main(String[] args) {

        Supplier<Stream<String>> stream = () -> IntStream.rangeClosed(273025, 767253).mapToObj(String::valueOf);

        long part1 = stream.get().filter(Day4::isValid).count();
        long part2 = stream.get().filter(Day4::isMoreValid).count();

        System.out.println(part1);
        System.out.println(part2);
    }

    private static boolean isValid(String variant) {
        boolean hasPair = false;
        for (int i = 1; i < variant.length(); i++) {
            if (variant.charAt(i - 1) > variant.charAt(i)) return false;
            if (variant.charAt(i - 1) == variant.charAt(i)) hasPair = true;
        }
        return hasPair;
    }

    private static boolean isMoreValid(String variant) {
        int[] inRow = new int[10];

        for (int i = 1; i < variant.length(); i++) {
            if (variant.charAt(i - 1) > variant.charAt(i)) return false;

            if (variant.charAt(i - 1) == variant.charAt(i)) {
                int number = variant.charAt(i) - '0';
                if (inRow[number] == 0) inRow[number] = 1;
                else inRow[number] = -1;
            }
        }
        return Arrays.stream(inRow).anyMatch(i -> i == 1);
    }

}
