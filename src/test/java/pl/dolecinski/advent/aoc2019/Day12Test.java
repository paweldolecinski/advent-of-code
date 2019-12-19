package pl.dolecinski.advent.aoc2019;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    private static Stream<Arguments> testCasesPart1() {
        return Stream.of(
            Arguments.of(List.of(
                List.of(-1, 0, 2),
                List.of(2, -10, -7),
                List.of(4, -8, 8),
                List.of(3, 5, -1)
            ), 10, 179),
            Arguments.of(List.of(
                List.of(-8, -10, 0),
                List.of(5, 5, 10),
                List.of(2, -7, 3),
                List.of(9, -8, -3)
            ), 100, 1940),
            Arguments.of(List.of(
                List.of(-16, -1, -12),
                List.of(0, -4, -17),
                List.of(-11, 11, 0),
                List.of(2, 2, -6)
            ), 1000, 5517)
        );
    }

    private static Stream<Arguments> testCasesPart2() {
        return Stream.of(
            Arguments.of(List.of(
                List.of(-1, 0, 2),
                List.of(2, -10, -7),
                List.of(4, -8, 8),
                List.of(3, 5, -1)
            ), 2772),
            Arguments.of(List.of(
                List.of(-8, -10, 0),
                List.of(5, 5, 10),
                List.of(2, -7, 3),
                List.of(9, -8, -3)
            ), 4686774924L),
            Arguments.of(List.of(
                List.of(-16, -1, -12),
                List.of(0, -4, -17),
                List.of(-11, 11, 0),
                List.of(2, 2, -6)
            ), 303_070_460_651_184L)
        );
    }

    @ParameterizedTest
    @MethodSource("testCasesPart1")
    void day12part1(List<List<Integer>> positions, int steps, int expectedTotalEnergy) {
        //given
        Day12.MotionSimluator simulator = new Day12.MotionSimluator(positions);

        //when
        simulator.run(steps);
        int totalEnergy = simulator.getTotalEnergy();

        //then
        if (expectedTotalEnergy == -1) {
            System.out.println(totalEnergy);
        } else {
            assertEquals(expectedTotalEnergy, totalEnergy);
        }
    }

    @ParameterizedTest
    @MethodSource("testCasesPart2")
    void day12part2(List<List<Integer>> positions, long expectedSteps) {
        //given
        Day12.MotionSimluator simulator = new Day12.MotionSimluator(positions);

        //when
        long steps = simulator.run();

        //then
        if (expectedSteps == -1) {
            System.out.println(steps);
        } else {
            assertEquals(expectedSteps, steps);
        }
    }

}
