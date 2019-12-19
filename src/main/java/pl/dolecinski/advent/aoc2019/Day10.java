package pl.dolecinski.advent.aoc2019;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class Day10 {
    
    static class AsteroidsMap {

        List<Asteroid> asteroids = new ArrayList<>();

        public AsteroidsMap(List<String> lines) {
            int row = 0;
            for (String line : lines) {
                char[] chars = line.toCharArray();
                for (int x = 0; x < chars.length; x++) {
                    if (chars[x] == '#') {
                        asteroids.add(new Asteroid(x, row));
                    }
                }
                row++;
            }

            for (Asteroid asteroid : asteroids) {
                asteroid.detect(asteroids);
            }
        }

        Asteroid findBestAsteroid() {
            Optional<Asteroid> best = asteroids.stream().max(Comparator.comparingInt(Asteroid::getDetectedNumber));
            return best.orElse(null);
        }
    }

    static class Asteroid {

        public int detectedNumber;
        public int x;
        public int y;

        Map<Double, PriorityQueue<Distance>> rays = new HashMap<>();

        public Asteroid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDetectedNumber() {
            return detectedNumber;
        }

        public void detect(List<Asteroid> asteroids) {

            for (Asteroid asteroid : asteroids) {
                if (this.equals(asteroid)) continue;

                double v = Math.atan2(asteroid.y - this.y, asteroid.x - this.x);
                PriorityQueue<Distance> rayLine = rays.computeIfAbsent(v, a -> new PriorityQueue<>());
                rayLine.add(new Distance(asteroid, Math.abs(asteroid.y - this.y) + Math.abs(asteroid.x - this.x)));

            }

            detectedNumber = rays.size();
        }

        public Asteroid vaporize(int howMany) {
            int counter = 0;
            Asteroid last = null;
            Set<Double> angels = rays.keySet();

            List<Double> clockwiseKeys = angels.stream().sorted((angle1, angle2) -> {
                if (angle1 < angle2) return 1;
                else if (angle2 < angle1) return -1;
                return 0;
            }).collect(Collectors.toList());

            int starter = 0;
            for (int i = 0; i < clockwiseKeys.size(); i++) {
                if (clockwiseKeys.get(i) <= -Math.PI / 2) {
                    starter = i;
                    break;
                }
            }
            int i = starter;
            while (counter < howMany) {
                Double rayKey = clockwiseKeys.get(i);
                Distance shot = rays.get(rayKey).poll();
                if (shot != null) {
                    counter++;
                    last = shot.asteroid;
                }
                if (i == 0) i = clockwiseKeys.size() - 1;
                else i--;
            }
            return last;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Asteroid asteroid = (Asteroid) o;
            return x == asteroid.x &&
                y == asteroid.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Distance implements Comparable<Distance> {
        Asteroid asteroid;
        int dist;

        public Distance(Asteroid asteroid, int i) {
            this.asteroid = asteroid;
            this.dist = i;
        }

        @Override
        public int compareTo(Distance o) {
            return this.dist - o.dist;
        }
    }
}
