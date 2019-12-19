package pl.dolecinski.advent.aoc2019;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @ParameterizedTest
    @CsvSource({
        "day10_1,      33, 5,8",
        "day10_2,      35, 1,2",
        "day10_3,      41, 6,3",
        "day10_4,      210, 11,13",
    })
    void shouldFindBestAsteroid(String filename, int detectedNumber, int x, int y) throws IOException, URISyntaxException {
        //given

        Path path = Paths.get("./" + filename);
        List<String> lines = Files.readAllLines(path);
        Day10.AsteroidsMap asteroidsMap = new Day10.AsteroidsMap(lines);

        //when
        Day10.Asteroid bestAsteroid = asteroidsMap.findBestAsteroid();

        //then
        assertEquals(detectedNumber, bestAsteroid.detectedNumber);
        assertEquals(x, bestAsteroid.x);
        assertEquals(y, bestAsteroid.y);
    }

    @Test
    void shouldFindBestAsteroid() throws IOException, URISyntaxException {
        //given

        Path path = Paths.get("./day10_part1");
        List<String> lines = Files.readAllLines(path);
        Day10.AsteroidsMap asteroidsMap = new Day10.AsteroidsMap(lines);

        //when
        Day10.Asteroid bestAsteroid = asteroidsMap.findBestAsteroid();

        //then
        System.out.println(bestAsteroid.detectedNumber);
    }

    @Test
    void part2() throws IOException, URISyntaxException {
        //given

        Path path = Paths.get("./day10_part1");
        List<String> lines = Files.readAllLines(path);
        Day10.AsteroidsMap asteroidsMap = new Day10.AsteroidsMap(lines);

        //when
        Day10.Asteroid bestAsteroid = asteroidsMap.findBestAsteroid();

        //then
        Day10.Asteroid last =  bestAsteroid.vaporize(200);

        System.out.println(last.x *100 +last.y);
    }
}
