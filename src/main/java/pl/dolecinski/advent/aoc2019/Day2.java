package pl.dolecinski.advent.aoc2019;

public class Day2 {

    public static void main(String[] args) {

        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                int[] intcode = {1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 13, 1, 19, 1, 6, 19, 23, 2, 23, 6, 27, 1, 5, 27, 31, 1, 10, 31, 35, 2, 6, 35, 39, 1, 39, 13, 43, 1, 43, 9, 47, 2, 47, 10, 51, 1, 5, 51, 55, 1, 55, 10, 59, 2, 59, 6, 63, 2, 6, 63, 67, 1, 5, 67, 71, 2, 9, 71, 75, 1, 75, 6, 79, 1, 6, 79, 83, 2, 83, 9, 87, 2, 87, 13, 91, 1, 10, 91, 95, 1, 95, 13, 99, 2, 13, 99, 103, 1, 103, 10, 107, 2, 107, 10, 111, 1, 111, 9, 115, 1, 115, 2, 119, 1, 9, 119, 0, 99, 2, 0, 14, 0};
                intcode[1] = noun;
                intcode[2] = verb;
                intprogram(intcode);
                if (intcode[0] == 19690720) {
                    System.out.println(100 * noun + verb);
                }
            }
        }
    }

    private static void intprogram(int[] intcode) {
        for (int i = 0; i < intcode.length - 4; i = i + 4) {
            int opt = intcode[i];
            int firstNumPos = intcode[i + 1];
            int secondNumPos = intcode[i + 2];
            int destPos = intcode[i + 3];
            if (opt == 99) {
                break;
            } else if (opt == 1) {
                intcode[destPos] = intcode[firstNumPos] + intcode[secondNumPos];
            } else if (opt == 2) {
                intcode[destPos] = intcode[firstNumPos] * intcode[secondNumPos];
            }
        }
    }
}
