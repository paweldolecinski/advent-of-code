package pl.dolecinski.advent.aoc2019;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @ParameterizedTest
    @CsvSource({
        "day14_1,      31",
        "day14_2,      165",
        "day14_3,      13312",
        "day14_4,      180697",
        "day14_5,      2210736",
        "day14_part1,  136771",
    })
    void shouldFindMinimumAmountOfOre(String filename, int expectedORE) throws IOException {
        //given

        Path path = Paths.get("./" + filename);
        List<String> lines = Files.readAllLines(path);
        Day14.Nanofactory nanofactory = new Day14.Nanofactory(lines);

        //when
        int oreAmount = nanofactory.fuelCost();

        //then
        assertEquals(expectedORE, oreAmount);
    }

}
