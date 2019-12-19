package pl.dolecinski.advent.aoc2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {

    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");

        List<String> strings = Files.readAllLines(Path.of(home + "/Downloads/input1.txt"));
        int a = 14 / 3 - 2;

        System.out.println(a);
        long result = 0;
        for (var string : strings) {
            long l = Long.parseLong(string);

            result += calcModule(l / 3 - 2);
        }
        System.out.println(result);
    }

    static long calcModule(long module) {
        if (module < 0) return 0;
        return module + calcModule(module / 3 - 2);
    }
}
