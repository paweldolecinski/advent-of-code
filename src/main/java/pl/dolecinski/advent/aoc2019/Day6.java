package pl.dolecinski.advent.aoc2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("/Users/pawel.dolecinski/Work/projects/sandbox/leetcode/src/main/java/pl/dolecinski/advent/aoc2019/input6.txt"));

        OrbitMap orbitMap = new OrbitMap(input);

        int orbits = orbitMap.orbits();

        int jumps = orbitMap.neededTransfers("YOU", "SAN");
        System.out.println("Part1: " + orbits);
        System.out.println("Part2: " + jumps);
    }

    static class OrbitMap {

        private static final String CENTER_OF_MASS = "COM";

        final Map<String, SpaceObject> objects = new HashMap<>();

        OrbitMap(List<String> input) {
            for (String line : input) {
                String[] ids = line.split("\\)");

                SpaceObject parent = objects.computeIfAbsent(ids[0], SpaceObject::new);
                SpaceObject child = objects.computeIfAbsent(ids[1], SpaceObject::new);

                parent.children.add(child);
                child.parent = parent;
            }
        }

        public int orbits() {
            return orbits(objects.get(CENTER_OF_MASS), 0);
        }

        private int orbits(SpaceObject root, int level) {
            int next = level + 1;
            for (SpaceObject child : root.children) {
                level += orbits(child, next);
            }
            return level;
        }

        public int neededTransfers(String startID, String destID) {

            SpaceObject start = objects.get(startID);
            SpaceObject destination = objects.get(destID);

            LinkedList<SpaceObject> pathToStart = new LinkedList<>();
            LinkedList<SpaceObject> pathToDest = new LinkedList<>();

            SpaceObject current = start;
            while ((current.parent != null)) {
                pathToStart.add(current.parent);
                current = current.parent;

            }

            current = destination;
            while ((current.parent != null)) {
                pathToDest.add(current.parent);
                current = current.parent;
            }

            while (!pathToStart.isEmpty() && !pathToDest.isEmpty()) {
                if (pathToStart.peekLast() != pathToDest.peekLast()) {
                    break;
                }
                pathToStart.pollLast();
                pathToDest.pollLast();
            }

            return pathToStart.size() + pathToDest.size();
        }

    }


    static class SpaceObject {
        String id;
        SpaceObject parent;
        List<SpaceObject> children = new ArrayList<>();

        public SpaceObject(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpaceObject that = (SpaceObject) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
